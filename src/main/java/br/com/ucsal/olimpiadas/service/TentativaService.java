package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Questao;
import br.com.ucsal.olimpiadas.domain.Tentativa;

import java.util.List;

public interface TentativaService {

    //Cria mas não persiste uma nova tentativa para o participante/prova
    Tentativa iniciar(long participanteId, long provaId);

    //Registra a resposta do participante em uma questão e a adiciona tentativa
    void adicionarResposta(Tentativa tentativa, Questao questao, char marcada);

    //Persiste a tentativa após todas as respostas registradas
    void finalizar(Tentativa tentativa);

    //Calcula a nota da tentativa usando a estratégia de pontuação configurada
    int calcularNota(Tentativa tentativa);

    //Retorna todas as tentativas finalizadas
    List<Tentativa> listarTodas();
}
