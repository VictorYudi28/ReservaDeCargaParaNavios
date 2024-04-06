package br.fiap.carga;

import br.fiap.cliente.Cliente;

import java.util.Random;

public class Carga {

    private int id;
    private String destino;
    private double peso;
    private Cliente cliente;

    public Carga(String destino, Cliente cliente, double peso) {
        this.destino = destino;
        this.cliente = cliente;
        this.peso = peso;

        Random random = new Random();

        gerarId(random);

    }

    public int getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDados(){

        String dados = "";

        dados += "Id: " + id + "\n"
                + "Destino: " + destino + "\n"
                + "Peso: " + peso + "\n"
                + cliente.getDados();

        return dados;
    }

    private void gerarId(Random random){

        this.id = random.nextInt(2500);

    }

}
