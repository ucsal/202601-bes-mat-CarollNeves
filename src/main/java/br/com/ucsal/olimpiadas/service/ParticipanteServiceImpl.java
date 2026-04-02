package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Participante;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;

import java.util.List;
import java.util.Optional;

public class ParticipanteServiceImpl implements ParticipanteService {

    private final ParticipanteRepository repository;

    public ParticipanteServiceImpl(ParticipanteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Participante cadastrar(String nome, String email) {
        var p = new Participante(nome, email);
        return repository.save(p);
    }

    @Override
    public List<Participante> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Participante> buscarPorId(long id) {
        return repository.findById(id);
    }
}
