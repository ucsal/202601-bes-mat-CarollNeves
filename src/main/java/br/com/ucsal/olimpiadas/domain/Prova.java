package br.com.ucsal.olimpiadas.domain;

public class Prova {

    private long id;
    private String titulo;

    // Cria uma prova já validando o título obrigatório
    public Prova(String titulo) {
        setTitulo(titulo);
    }

    //Construtor
    public Prova() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    //throws new IllegalArgumentException se o título for nulo ou em branco
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("título inválido");
        }
        this.titulo = titulo;
    }
}
