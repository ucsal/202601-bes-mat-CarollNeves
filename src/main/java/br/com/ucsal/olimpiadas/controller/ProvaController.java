package br.com.ucsal.olimpiadas.controller;

import br.com.ucsal.olimpiadas.service.ProvaService;
import br.com.ucsal.olimpiadas.view.InputView;
import br.com.ucsal.olimpiadas.view.OutputView;

public class ProvaController {

    private final ProvaService service;
    private final InputView input;
    private final OutputView output;

    public ProvaController(ProvaService service, InputView input, OutputView output) {
        this.service = service;
        this.input = input;
        this.output = output;
    }

    // Solicita título e cadastra prova
    public void cadastrar() {
        output.imprimir("Título da prova: ");
        String titulo = input.lerLinha();

        try {
            var prova = service.cadastrar(titulo);
            output.imprimirLinha("Prova criada: " + prova.getId());
        } catch (IllegalArgumentException e) {
            output.imprimirLinha(e.getMessage());
        }
    }

    // Exibe a lista de provas e pergunta qual o id
    public Long escolher() {
        output.imprimirLinha("\nProvas:");
        for (var p : service.listarTodas()) {
            output.imprimirFormatado("  %d) %s%n", p.getId(), p.getTitulo());
        }
        output.imprimir("Escolha o id da prova: ");

        try {
            long id = Long.parseLong(input.lerLinha());
            if (service.buscarPorId(id).isEmpty()) {
                output.imprimirLinha("id inválido");
                return null;
            }
            return id;
        } catch (NumberFormatException e) {
            output.imprimirLinha("entrada inválida");
            return null;
        }
    }

    // Retorna true se há pelo menos uma prova cadastrada
    public boolean temProvas() {
        return !service.listarTodas().isEmpty();
    }
}
