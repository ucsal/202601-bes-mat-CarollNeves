package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Tentativa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InMemoryTentativaRepository implements TentativaRepository {

    private final List<Tentativa> store = new ArrayList<>();
    private long nextId = 1;

    @Override
    public Tentativa save(Tentativa t) {
        if (t.getId() == 0) {
            t.setId(nextId++);
        }
        store.add(t);
        return t;
    }

    @Override
    public Optional<Tentativa> findById(Long id) {
        return store.stream().filter(t -> t.getId() == id).findFirst();
    }

    @Override
    public List<Tentativa> findAll() {
        return Collections.unmodifiableList(store);
    }
}
