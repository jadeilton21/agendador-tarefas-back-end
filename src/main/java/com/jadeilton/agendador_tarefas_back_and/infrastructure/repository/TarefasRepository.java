package com.jadeilton.agendador_tarefas_back_and.infrastructure.repository;

import com.jadeilton.agendador_tarefas_back_and.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity,String> {
}
