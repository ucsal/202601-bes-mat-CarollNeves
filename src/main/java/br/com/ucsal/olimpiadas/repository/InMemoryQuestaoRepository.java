package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Questao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InMemoryQuestaoRepository implements QuestaoRepository {

    private final List<Questao> store = new ArrayList<>();
    private long nextId = 1;

    @Override
    public Questao save(Questao q) {
        if (q.getId() == 0) {
            q.setId(nextId++);
        }
        store.add(q);
        return q;
    }

    @Override
    public Optional<Questao> findById(Long id) {
        return store.stream().filter(q -> q.getId() == id).findFirst();
    }

    @Override
    public List<Questao> findAll() {
        return Collections.unmodifiableList(store);
    }

    @Override
    public List<Questao> findByProvaId(long provaId) {
        return store.stream().filter(q -> q.getProvaId() == provaId).toList();
    }
}
