package br.com.ucsal.olimpiadas.view;

public interface OutputView {

    //Imprime code mensagem sem quebra de linha para prompts inline
    void imprimir(String mensagem);

    //Imprime code mensagem seguida de quebra de linha
    void imprimirLinha(String mensagem);

    //Imprime uma linha em branco
    void imprimirLinha();

    //Imprime usando formatação estilo {@link java.util.Formatter}
    void imprimirFormatado(String formato, Object... args);
}
