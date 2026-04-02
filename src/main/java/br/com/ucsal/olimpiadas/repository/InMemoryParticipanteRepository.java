package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Participante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InMemoryParticipanteRepository implements ParticipanteRepository {

    private final List<Participante> store = new ArrayList<>();
    private long nextId = 1;

    @Override
    public Participante save(Participante p) {
        if (p.getId() == 0) {
            p.setId(nextId++);
        }
        store.add(p);
        return p;
    }

    @Override
    public Optional<Participante> findById(Long id) {
        return store.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public List<Participante> findAll() {
        return Collections.unmodifiableList(store);
    }
}
