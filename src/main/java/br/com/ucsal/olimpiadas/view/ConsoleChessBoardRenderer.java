package br.com.ucsal.olimpiadas.view;

public class ConsoleChessBoardRenderer implements ChessBoardRenderer {

    private final OutputView output;

    public ConsoleChessBoardRenderer(OutputView output) {
        this.output = output;
    }

    @Override
    public void renderizar(String fen) {
        if (fen == null || fen.isBlank()) {
            return;
        }

        String parteTabuleiro = fen.split(" ")[0];
        String[] ranks = parteTabuleiro.split("/");

        output.imprimirLinha();
        output.imprimirLinha("    a b c d e f g h");
        output.imprimirLinha("   -----------------");

        for (int r = 0; r < 8; r++) {
            String rank = ranks[r];
            var sb = new StringBuilder();
            sb.append(8 - r).append(" | ");

            for (char c : rank.toCharArray()) {
                if (Character.isDigit(c)) {
                    int vazios = c - '0';
                    for (int i = 0; i < vazios; i++) {
                        sb.append(". ");
                    }
                } else {
                    sb.append(c).append(' ');
                }
            }

            sb.append("| ").append(8 - r);
            output.imprimirLinha(sb.toString());
        }

        output.imprimirLinha("   -----------------");
        output.imprimirLinha("    a b c d e f g h");
        output.imprimirLinha();
    }
}
