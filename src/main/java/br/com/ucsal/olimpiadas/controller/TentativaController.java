package br.com.ucsal.olimpiadas.controller;

import br.com.ucsal.olimpiadas.domain.Questao;
import br.com.ucsal.olimpiadas.service.QuestaoService;
import br.com.ucsal.olimpiadas.service.TentativaService;
import br.com.ucsal.olimpiadas.view.ChessBoardRenderer;
import br.com.ucsal.olimpiadas.view.InputView;
import br.com.ucsal.olimpiadas.view.OutputView;

public class TentativaController {

    private final TentativaService tentativaService;
    private final QuestaoService questaoService;
    private final ParticipanteController participanteController;
    private final ProvaController provaController;
    private final InputView input;
    private final OutputView output;
    private final ChessBoardRenderer chessBoardRenderer;

    public TentativaController(TentativaService tentativaService,
                               QuestaoService questaoService,
                               ParticipanteController participanteController,
                               ProvaController provaController,
                               InputView input,
                               OutputView output,
                               ChessBoardRenderer chessBoardRenderer) {
        this.tentativaService = tentativaService;
        this.questaoService = questaoService;
        this.participanteController = participanteController;
        this.provaController = provaController;
        this.input = input;
        this.output = output;
        this.chessBoardRenderer = chessBoardRenderer;
    }

    // Seleciona participante, prova e coleta respostas
    public void aplicarProva() {
        if (!participanteController.temParticipantes()) {
            output.imprimirLinha("cadastre participantes primeiro");
            return;
        }
        if (!provaController.temProvas()) {
            output.imprimirLinha("cadastre provas primeiro");
            return;
        }

        Long participanteId = participanteController.escolher();
        if (participanteId == null) return;

        Long provaId = provaController.escolher();
        if (provaId == null) return;

        var questoesDaProva = questaoService.listarPorProva(provaId);
        if (questoesDaProva.isEmpty()) {
            output.imprimirLinha("esta prova não possui questões cadastradas");
            return;
        }

        var tentativa = tentativaService.iniciar(participanteId, provaId);
        output.imprimirLinha("\n--- Início da Prova ---");

        for (var q : questoesDaProva) {
            output.imprimirLinha("\nQuestão #" + q.getId());
            output.imprimirLinha(q.getEnunciado());

            if (q.getFenInicial() != null && !q.getFenInicial().isBlank()) {
                output.imprimirLinha("Posição inicial:");
                chessBoardRenderer.renderizar(q.getFenInicial());
            }

            for (var alt : q.getAlternativas()) {
                output.imprimirLinha(alt);
            }

            output.imprimir("Sua resposta (A–E): ");
            char marcada;
            try {
                marcada = Questao.normalizar(input.lerLinha().trim().charAt(0));
            } catch (Exception e) {
                output.imprimirLinha("resposta inválida (marcando como errada)");
                marcada = 'X'; //trata caractere inválido como errado
            }

            tentativaService.adicionarResposta(tentativa, q, marcada);
        }

        tentativaService.finalizar(tentativa);

        int nota = tentativaService.calcularNota(tentativa);
        output.imprimirLinha("\n--- Fim da Prova ---");
        output.imprimirLinha("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }

    // Lista todas as tentativas com um resumo de cada uma
    public void listarTentativas() {
        output.imprimirLinha("\n--- Tentativas ---");
        for (var t : tentativaService.listarTodas()) {
            output.imprimirFormatado("#%d | participante=%d | prova=%d | nota=%d/%d%n",
                    t.getId(),
                    t.getParticipanteId(),
                    t.getProvaId(),
                    tentativaService.calcularNota(t),
                    t.getRespostas().size());
        }
    }
}
