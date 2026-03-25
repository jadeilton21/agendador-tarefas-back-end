package com.jadeilton.agendador_tarefas_back_and.infrastructure.repository;

import com.jadeilton.agendador_tarefas_back_and.infrastructure.entity.TarefasEntity;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.enums.StatusNotificacoEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetweenAndStatusNotificacoEnum(
            LocalDateTime dataInicial,
            LocalDateTime dataFinal,
            StatusNotificacoEnum statusNotificacoEnum);

    List<TarefasEntity> findByEmailUsuario(String email);
}