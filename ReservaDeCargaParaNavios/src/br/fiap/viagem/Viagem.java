package br.fiap.viagem;

import static javax.swing.JOptionPane.*;
import br.fiap.carga.Carga;

public class Viagem {

    private double capacidade;
    private int index;
    private Carga[] carga;

    public double getCapacidade() {
        return capacidade;
    }

    public Viagem() {

        this.capacidade = 10000;
        this.index = 0;
        this.carga = new Carga[20];

    }

    public boolean reservar(Carga carga){

        if(permitidoReservar(carga.getPeso())){

            this.carga[index] = carga;
            index++;

            return true;

        }else{
            return false;
        }

    }

    private boolean permitidoReservar(double peso){

        if(peso <= this.capacidade){
            this.capacidade -= peso;
            return true;
        }else{
            return false;
        }

    }

    public double capacidadeReservada(){

        double total = 0;

        for(int i = 0; i < index; i++){
            total += this.carga[i].getPeso();
        }

        return total;

    }

    public void pesquisarCarga(int cnpj){

        int cont = 0;

        for(int i = 0; i < index; i++){

            if(carga[i].getCliente().getCnpj() == cnpj){

                cont++;
                showMessageDialog(null,carga[i].getDados());

            }

        }

        if(cont == 0){
            showMessageDialog(null,"O CNPJ " + cnpj + " não tem reserva");
        }

    }

    public void visualizarCargas(){

        for(int i = 0; i < index; i++){

            showMessageDialog(null,carga[i].getDados());

        }

        if(index == 0){
            showMessageDialog(null,"Não há cargas reservadas !");
        }

    }

    public boolean verificaIdCarga(int idCarga){

        for(int i = 0; i < index; i++){

            if(carga[i].getId() == idCarga){
                return true;
            }

        }

        return false;

    }

    public boolean cancelarReserva(int id){

        int p = encontrarCarga(id);

        if(p != -1){

            for(int i = p; i < index - 1; i++){
                carga[i] = carga[i+1];
            }

            index--;

            return true;

        }

        return false;

    }

    private int encontrarCarga(int id){

        for(int i = 0; i <= index - 1; i++){

            if(carga[i].getId() == id){

                return i;

            }

        }

        return -1;

    }

}
