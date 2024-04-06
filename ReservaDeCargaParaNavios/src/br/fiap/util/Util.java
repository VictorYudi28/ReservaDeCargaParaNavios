package br.fiap.util;

import br.fiap.carga.Carga;
import br.fiap.cliente.Cliente;
import br.fiap.viagem.Viagem;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.*;

public class Util {
    private Map<Integer, Cliente> clientes = new HashMap<>();
    private Viagem viagem = new Viagem();
    public void executar(){

        int opcao;

        do{

            String auxOpcao = showInputDialog(null, "1. Cadastrar Cliente" + "\n" +
                    "2. Realizar reserva" + "\n" +
                    "3. Pesquisar reserva" + "\n" +
                    "4. Visualizar reservas" + "\n" +
                    "5. Capacidade reservada" + "\n" +
                    "6. Cancelar reserva" + "\n" +
                    "7. Encerrar programa" + "\n");

            opcao = Integer.parseInt(auxOpcao);

            if(opcao <= 0 || opcao > 7){
                showMessageDialog(null,"Opção inválida !");
            }

            switch (opcao){
                case 1:
                    cadastrar();
                    break;
                case 2:
                    reservar();
                    break;
                case 3:
                    pesquisar();
                    break;
                case 4:
                    visualizarReserva();
                    break;
                case 5:
                    verificarCapacidadeReservada();
                    break;
                case 6:
                    cancelarReserva();
                    break;
                case 7:
                    encerrar();
                    break;
                default:
                    break;
            }

        }while (opcao <= 0 || opcao > 7);



    }

    public void exibirNomeOpcao(String nome){
        showMessageDialog(null,nome);
    }

    public void cadastrar(){

        exibirNomeOpcao("Cadastrar cliente");

        int cnpj;

        cnpj = Integer.parseInt(showInputDialog(null, "Informe o CNPJ: "));

        cnpj = verificaCadastro(cnpj);

        String nome = showInputDialog(null,"Informe o nome do cliente: ");

        clientes.put(cnpj,new Cliente(cnpj,nome));

        showMessageDialog(null,"Cliente cadastrado com sucesso");

        this.executar();

    }

    public void reservar(){

        exibirNomeOpcao("Realizar reserva");

        int cnpj,resp;

        do {

            cnpj = Integer.parseInt(showInputDialog(null,"Informe o CNPJ: "));

            if(!clientes.containsKey(cnpj)){
                showMessageDialog(null,"CNPJ não está cadastrado !");

                do{

                    resp = Integer.parseInt(showInputDialog(null,"Digite 1 - para cadastrar o CNPJ"
                            + "\n" + "Digite 2 - para digitar novamente"
                            + "\n" + "Digite 3 para voltar ao menu principal"));

                    if(resp != 1 && resp != 2 && resp != 3){
                        showMessageDialog(null,"Opção inválida - digite novamente");
                    }

                }while (resp != 1 && resp != 2 && resp != 3);


                switch (resp){
                    case 1: cadastrar();
                        break;
                    case 2: reservar();
                        break;
                    case 3: this.executar();
                    default:
                        break;
                }

            }

        }while (!clientes.containsKey(cnpj));

        Cliente cliente = clientes.get(cnpj);

        String destino = showInputDialog(null, "Informe o destino: ");

        double peso = Double.parseDouble(showInputDialog(null,"Informe o peso: "));

        Carga carga = new Carga(destino,cliente,peso);

        boolean reservar = viagem.reservar(carga);

        if(reservar){
            showMessageDialog(null,"Carga reservada !");
        }else{
            showMessageDialog(null,"Não foi possível reservar a carga !");
        }

        this.executar();

    }

    public void pesquisar(){

        exibirNomeOpcao("Pesquisar reserva");

        int cnpj,resp;

        do {

            cnpj = Integer.parseInt(showInputDialog(null,"Informe o CNPJ: "));

            if(!clientes.containsKey(cnpj)){
                showMessageDialog(null,"CNPJ não encontrado");

                do{

                    resp = Integer.parseInt(showInputDialog(null,"Digite 1 - para digitar novamente"
                            + "\n" + "Digite 2 - para voltar ao menu principal"));

                    if(resp != 1 && resp != 2){
                        showMessageDialog(null,"Opção inválida - digite novamente");
                    }

                }while (resp != 1 && resp != 2);


                switch (resp){
                    case 1: pesquisar();
                        break;
                    case 2: this.executar();
                        break;
                    default:
                        break;
                }

            }

        }while (!clientes.containsKey(cnpj));

        viagem.pesquisarCarga(cnpj);

        this.executar();

    }

    public void visualizarReserva(){

        exibirNomeOpcao("Visualizar reservas");

        viagem.visualizarCargas();

        this.executar();

    }

    public void verificarCapacidadeReservada(){

        exibirNomeOpcao("Capacidade reservada");

        double capacidade = viagem.capacidadeReservada();

        showMessageDialog(null,"Capacidade reservada do navio: " + capacidade);

        this.executar();

    }

    public void cancelarReserva(){

        exibirNomeOpcao("Cancelar reserva");

        int idCarga , resp = 0;
        boolean verificaId;

        do{
            idCarga = Integer.parseInt(showInputDialog(null,"Digite o ID da carga que deseja remover"));

            verificaId = viagem.verificaIdCarga(idCarga);

            if(verificaId == false){
                showMessageDialog(null,"ID não encontrado");

                do{

                    resp = Integer.parseInt(showInputDialog(null,"Digite 1 - para digitar novamente"
                            + "\n" + "Digite 2 - para voltar ao menu principal"));

                    if(resp != 1 && resp != 2){
                        showMessageDialog(null,"Opção inválida - digite novamente");
                    }

                }while (resp != 1 && resp != 2);

                switch (resp) {
                    case 1:
                        break;
                    case 2:
                        this.executar();
                        break;
                    default:
                        break;
                }

            }

        }while (verificaId == false);

        boolean verificaCancelamentoReserva = viagem.cancelarReserva(idCarga);

        if(verificaCancelamentoReserva){
            showMessageDialog(null,"Reserva cancelada com sucesso");
        }

        this.executar();

    }

    public void encerrar(){

        showMessageDialog(null,"Programa encerrado");

    }

    private int verificaCadastro(int cnpj){

        int resp = 0;

        while (clientes.containsKey(cnpj)){

            showMessageDialog(null,"CNPJ já está cadastrado !");

            resp = showConfirmDialog(null,"Deseja digitar novamente ?");

            if(resp != YES_NO_OPTION){
                this.executar();
            }else{
                cnpj = Integer.parseInt(showInputDialog(null,"Informe o CNPJ: "));
            }

        }

        return cnpj;

    }

}
