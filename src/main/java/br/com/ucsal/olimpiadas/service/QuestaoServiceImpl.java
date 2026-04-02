package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Questao;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;

import java.util.List;
import java.util.Optional;

public class QuestaoServiceImpl implements QuestaoService {

    private final QuestaoRepository repository;

    public QuestaoServiceImpl(QuestaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Questao cadastrar(long provaId, String enunciado, String[] alternativas,
                             char alternativaCorreta, String fenInicial) {
        var q = new Questao.Builder()
                .provaId(provaId)
                .enunciado(enunciado)
                .alternativas(alternativas)
                .alternativaCorreta(alternativaCorreta)
                .fenInicial(fenInicial)
                .build();
        return repository.save(q);
    }

    @Override
    public List<Questao> listarPorProva(long provaId) {
        return repository.findByProvaId(provaId);
    }

    @Override
    public Optional<Questao> buscarPorId(long id) {
        return repository.findById(id);
    }
}
