package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Questao;

import java.util.List;

//Repositório para questão
public interface QuestaoRepository extends CrudRepository<Questao, Long> {

    //Retorna todas as questões da prova específica
    List<Questao> findByProvaId(long provaId);
}
