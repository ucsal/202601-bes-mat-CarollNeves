package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Prova;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InMemoryProvaRepository implements ProvaRepository {

    private final List<Prova> store = new ArrayList<>();
    private long nextId = 1;

    @Override
    public Prova save(Prova prova) {
        if (prova.getId() == 0) {
            prova.setId(nextId++);
        }
        store.add(prova);
        return prova;
    }

    @Override
    public Optional<Prova> findById(Long id) {
        return store.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public List<Prova> findAll() {
        return Collections.unmodifiableList(store);
    }
}
