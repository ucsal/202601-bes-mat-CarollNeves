package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Questao;
import br.com.ucsal.olimpiadas.domain.Resposta;
import br.com.ucsal.olimpiadas.domain.Tentativa;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;
import br.com.ucsal.olimpiadas.scoring.ScoringStrategy;

import java.util.List;

public class TentativaServiceImpl implements TentativaService {

    private final TentativaRepository repository;
    private final ScoringStrategy scoringStrategy;

    public TentativaServiceImpl(TentativaRepository repository, ScoringStrategy scoringStrategy) {
        this.repository = repository;
        this.scoringStrategy = scoringStrategy;
    }

    @Override
    public Tentativa iniciar(long participanteId, long provaId) {
        var t = new Tentativa();
        t.setParticipanteId(participanteId);
        t.setProvaId(provaId);
        return t;
    }

    @Override
    public void adicionarResposta(Tentativa tentativa, Questao questao, char marcada) {
        var r = new Resposta();
        r.setQuestaoId(questao.getId());
        r.setAlternativaMarcada(marcada);
        boolean correta;
        try {
            correta = questao.isRespostaCorreta(marcada);
        } catch (IllegalArgumentException e) {
            correta = false;
        }
        r.setCorreta(correta);
        tentativa.getRespostas().add(r);
    }

    @Override
    public void finalizar(Tentativa tentativa) {
        repository.save(tentativa);
    }

    @Override
    public int calcularNota(Tentativa tentativa) {
        return scoringStrategy.calcularNota(tentativa);
    }

    @Override
    public List<Tentativa> listarTodas() {
        return repository.findAll();
    }
}
