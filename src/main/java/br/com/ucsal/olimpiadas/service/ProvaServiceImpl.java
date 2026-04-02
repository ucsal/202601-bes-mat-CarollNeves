package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Prova;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;

import java.util.List;
import java.util.Optional;

public class ProvaServiceImpl implements ProvaService {

    private final ProvaRepository repository;

    public ProvaServiceImpl(ProvaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Prova cadastrar(String titulo) {
        var prova = new Prova(titulo);
        return repository.save(prova);
    }

    @Override
    public List<Prova> listarTodas() {
        return repository.findAll();
    }

    @Override
    public Optional<Prova> buscarPorId(long id) {
        return repository.findById(id);
    }
}
