package br.com.ucsal.olimpiadas.domain;

//Representa um participante da olimpíada
public class Participante {

    private long id;
    private String nome;
    private String email;

    // Cria um participante já validando o nome obrigatório.
    public Participante(String nome, String email) {
        setNome(nome);
        this.email = email;
    }

    //Construtor
    public Participante() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    //throws new IllegalArgumentException se o nome for nulo ou em branco
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("nome inválido");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
