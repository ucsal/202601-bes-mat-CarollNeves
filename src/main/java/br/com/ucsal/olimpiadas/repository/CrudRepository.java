package br.com.ucsal.olimpiadas.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    //Persiste a entidade e a retorna
    T save(T entity);

    //Busca uma entidade pelo id
    Optional<T> findById(ID id);

    //Retorna todas as entidades
    List<T> findAll();
}
