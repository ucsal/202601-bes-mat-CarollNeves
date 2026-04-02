package br.com.ucsal.olimpiadas.scoring;

import br.com.ucsal.olimpiadas.domain.Tentativa;

public interface ScoringStrategy {

    //Calcula a nota de uma tentativa
    int calcularNota(Tentativa tentativa);
}
