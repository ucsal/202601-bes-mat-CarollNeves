package br.com.ucsal.olimpiadas.controller;

import br.com.ucsal.olimpiadas.domain.Questao;
import br.com.ucsal.olimpiadas.service.QuestaoService;
import br.com.ucsal.olimpiadas.view.InputView;
import br.com.ucsal.olimpiadas.view.OutputView;

public class QuestaoController {

    private final QuestaoService questaoService;
    private final ProvaController provaController;
    private final InputView input;
    private final OutputView output;

    public QuestaoController(QuestaoService questaoService,
                             ProvaController provaController,
                             InputView input,
                             OutputView output) {
        this.questaoService = questaoService;
        this.provaController = provaController;
        this.input = input;
        this.output = output;
    }

    // Coleta dados da questão e usuário e cadastra na prova escolhida
    public void cadastrar() {
        if (!provaController.temProvas()) {
            output.imprimirLinha("não há provas cadastradas");
            return;
        }

        Long provaId = provaController.escolher();
        if (provaId == null) return;

        output.imprimirLinha("Enunciado:");
        String enunciado = input.lerLinha();

        String[] alternativas = new String[5];
        for (int i = 0; i < 5; i++) {
            char letra = (char) ('A' + i);
            output.imprimir("Alternativa " + letra + ": ");
            alternativas[i] = letra + ") " + input.lerLinha();
        }

        output.imprimir("Alternativa correta (A–E): ");
        char correta;
        try {
            correta = Questao.normalizar(input.lerLinha().trim().charAt(0));
        } catch (Exception e) {
            output.imprimirLinha("alternativa inválida");
            return;
        }

        var q = questaoService.cadastrar(provaId, enunciado, alternativas, correta, null);
        output.imprimirLinha("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
    }
}
