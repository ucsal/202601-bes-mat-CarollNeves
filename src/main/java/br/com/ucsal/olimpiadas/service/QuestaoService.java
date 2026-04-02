package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Questao;

import java.util.List;
import java.util.Optional;

public interface QuestaoService {

    //Cadastra uma questão na prova indicada
    Questao cadastrar(long provaId, String enunciado, String[] alternativas,
                      char alternativaCorreta, String fenInicial);

    //Retorna todas as questões de uma prova
    List<Questao> listarPorProva(long provaId);

    //Busca uma questão pelo id
    Optional<Questao> buscarPorId(long id);
}
