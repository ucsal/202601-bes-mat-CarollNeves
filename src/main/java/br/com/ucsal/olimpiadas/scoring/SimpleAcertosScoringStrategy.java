package br.com.ucsal.olimpiadas.scoring;

import br.com.ucsal.olimpiadas.domain.Tentativa;

//Nota = número de acertos
public class SimpleAcertosScoringStrategy implements ScoringStrategy {

    @Override
    public int calcularNota(Tentativa tentativa) {
        int acertos = 0;
        for (var r : tentativa.getRespostas()) {
            if (r.isCorreta()) {
                acertos++;
            }
        }
        return acertos;
    }
}
