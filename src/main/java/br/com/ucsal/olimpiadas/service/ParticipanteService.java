package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Participante;

import java.util.List;
import java.util.Optional;

public interface ParticipanteService {

    //Valida e cadastra novo participante
    Participante cadastrar(String nome, String email);

    //Retorna participantes cadastrados
    List<Participante> listarTodos();

    //Busca um participante pelo id
    Optional<Participante> buscarPorId(long id);
}
