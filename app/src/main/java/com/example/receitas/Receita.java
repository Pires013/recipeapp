package com.example.receitas;

public class Receita {
    private final String nome;
    private final int imagem;
    private final String ingredientes;
    private final String preparo;

    public Receita(String nome, int imagem, String ingredientes, String preparo) {
        this.nome = nome;
        this.imagem = imagem;
        this.ingredientes = ingredientes;
        this.preparo = preparo;
    }

    public String getNome() {
        return nome;
    }

    public int getImagem() {
        return imagem;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getPreparo() {
        return preparo;
    }
}
