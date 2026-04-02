package br.com.ucsal.olimpiadas.controller;

import br.com.ucsal.olimpiadas.view.InputView;
import br.com.ucsal.olimpiadas.view.OutputView;
 
public class MenuController {

    private final ParticipanteController participanteController;
    private final ProvaController provaController;
    private final QuestaoController questaoController;
    private final TentativaController tentativaController;
    private final InputView input;
    private final OutputView output;

    public MenuController(ParticipanteController participanteController,
                          ProvaController provaController,
                          QuestaoController questaoController,
                          TentativaController tentativaController,
                          InputView input,
                          OutputView output) {
        this.participanteController = participanteController;
        this.provaController = provaController;
        this.questaoController = questaoController;
        this.tentativaController = tentativaController;
        this.input = input;
        this.output = output;
    }

    // Inicia o while de menu e finaliza quando o usuário escolher a opção 0
    public void run() {
        while (true) {
            output.imprimirLinha("\n=== OLIMPÍADA ===");
            output.imprimirLinha("1) Cadastrar participante:");
            output.imprimirLinha("2) Cadastrar prova:");
            output.imprimirLinha("3) Cadastrar questão (A–E) na prova:");
            output.imprimirLinha("4) Aplicar prova (adicionar participante + prova):");
            output.imprimirLinha("5) Listar tentativas (resumo):");
            output.imprimirLinha("0) Sair:");
            output.imprimir("> ");

            switch (input.lerLinha()) {
                case "1" -> participanteController.cadastrar();
                case "2" -> provaController.cadastrar();
                case "3" -> questaoController.cadastrar();
                case "4" -> tentativaController.aplicarProva();
                case "5" -> tentativaController.listarTentativas();
                case "0" -> {
                    output.imprimirLinha("tchau");
                    return;
                }
                default -> output.imprimirLinha("opção inválida");
            }
        }
    }
}
