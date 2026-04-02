package br.com.ucsal.olimpiadas.view;

public class ConsoleOutputView implements OutputView {

    @Override
    public void imprimir(String mensagem) {
        System.out.print(mensagem);
    }

    @Override
    public void imprimirLinha(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void imprimirLinha() {
        System.out.println();
    }

    @Override
    public void imprimirFormatado(String formato, Object... args) {
        System.out.printf(formato, args);
    }
}
