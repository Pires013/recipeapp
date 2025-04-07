    package com.example.receitas;

    public class Receita {
        private String nome;
        private int imagem;
        private String ingredientes;
        private String preparo;
        private boolean favorito;

        public Receita(String nome, int imagem, String ingredientes, String preparo) {
            this.nome = nome;
            this.imagem = imagem;
            this.ingredientes = ingredientes;
            this.preparo = preparo;
            this.favorito = false;
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

        public boolean isFavorito() {
            return favorito;
        }

        public void setFavorito(boolean favorito) {
            this.favorito = favorito;
        }
    }
