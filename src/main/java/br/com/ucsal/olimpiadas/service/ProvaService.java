package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Prova;

import java.util.List;
import java.util.Optional;

public interface ProvaService {

    //Valida e cadastra nova prova
    Prova cadastrar(String titulo);

    //Retorna todas as provas cadastradas
    List<Prova> listarTodas();

    //Busca uma prova pelo id
    Optional<Prova> buscarPorId(long id);
}
