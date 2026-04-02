package br.com.ucsal.olimpiadas.domain;

import java.util.Arrays;

public class Questao {

    private long id;
    private long provaId;
    private String enunciado;
    private String[] alternativas = new String[5];
    private char alternativaCorreta;
    private String fenInicial;

    //Getters / Setters

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getProvaId() { return provaId; }
    public void setProvaId(long provaId) { this.provaId = provaId; }

    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }

    public String[] getAlternativas() { return alternativas; }

    //throws new IllegalArgumentException se o array não tiver exatamente 5 elementos
    public void setAlternativas(String[] alternativas) {
        if (alternativas == null || alternativas.length != 5) {
            throw new IllegalArgumentException("A questão deve possuir exatamente 5 alternativas.");
        }
        this.alternativas = Arrays.copyOf(alternativas, 5);
    }

    public char getAlternativaCorreta() { return alternativaCorreta; }

    public void setAlternativaCorreta(char alternativaCorreta) {
        this.alternativaCorreta = normalizar(alternativaCorreta);
    }

    public String getFenInicial() { return fenInicial; }
    public void setFenInicial(String fenInicial) { this.fenInicial = fenInicial; }

    //Lógica de domínio

    //Verifica se a alternativa marcada é a alternativa certa
    public boolean isRespostaCorreta(char marcada) {
        return normalizar(marcada) == alternativaCorreta;
    }

    //Normaliza a letra de uma alternativa para maiúscula e valida o intervalo A–E
     
    public static char normalizar(char c) {
        char up = Character.toUpperCase(c);
        if (up < 'A' || up > 'E') {
            throw new IllegalArgumentException("Alternativa deve estar entre A e E.");
        }
        return up;
    }

    //Builder

    public static class Builder {

        private long provaId;
        private String enunciado;
        private String[] alternativas;
        private char alternativaCorreta;
        private String fenInicial;

        public Builder provaId(long provaId) {
            this.provaId = provaId;
            return this;
        }

        public Builder enunciado(String enunciado) {
            this.enunciado = enunciado;
            return this;
        }

        public Builder alternativas(String[] alternativas) {
            this.alternativas = alternativas;
            return this;
        }

        public Builder alternativaCorreta(char alternativaCorreta) {
            this.alternativaCorreta = alternativaCorreta;
            return this;
        }

        public Builder fenInicial(String fenInicial) {
            this.fenInicial = fenInicial;
            return this;
        }

        //Constrói e retorna a questão configurada
        public Questao build() {
            var q = new Questao();
            q.setProvaId(provaId);
            q.setEnunciado(enunciado);
            q.setAlternativas(alternativas);
            q.setAlternativaCorreta(alternativaCorreta);
            q.setFenInicial(fenInicial);
            return q;
        }
    }
}
