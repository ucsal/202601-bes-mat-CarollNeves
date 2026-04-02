package br.com.ucsal.olimpiadas.controller;

import br.com.ucsal.olimpiadas.service.ParticipanteService;
import br.com.ucsal.olimpiadas.view.InputView;
import br.com.ucsal.olimpiadas.view.OutputView;

public class ParticipanteController {

    private final ParticipanteService service;
    private final InputView input;
    private final OutputView output;

    public ParticipanteController(ParticipanteService service, InputView input, OutputView output) {
        this.service = service;
        this.input = input;
        this.output = output;
    }

    // Solicita nome e e-mail e cadastra participante
    public void cadastrar() {
        output.imprimir("Nome: ");
        String nome = input.lerLinha();

        output.imprimir("Email (opcional): ");
        String email = input.lerLinha();

        try {
            var p = service.cadastrar(nome, email);
            output.imprimirLinha("Participante cadastrado: " + p.getId());
        } catch (IllegalArgumentException e) {
            output.imprimirLinha(e.getMessage());
        }
    }

    // Exibe a lista de participantes e pergunta qual o id
    public Long escolher() {
        output.imprimirLinha("\nParticipantes:");
        for (var p : service.listarTodos()) {
            output.imprimirFormatado("  %d) %s%n", p.getId(), p.getNome());
        }
        output.imprimir("Escolha o id do participante: ");

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

    // Retorna true se há pelo menos um participante cadastrado
    public boolean temParticipantes() {
        return !service.listarTodos().isEmpty();
    }
}
