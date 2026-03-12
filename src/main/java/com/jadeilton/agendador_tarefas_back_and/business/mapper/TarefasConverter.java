package com.jadeilton.agendador_tarefas_back_and.business.mapper;


import com.jadeilton.agendador_tarefas_back_and.business.dto.TarefasDTO;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {



    @Mapping(source = "id" , target = "id")
    @Mapping(source = "dataEvento" , target = "dataEvento")
    @Mapping(source = "dataCriacao" , target = "dataCriacao")

    TarefasEntity paraTarefaEntity(TarefasDTO dto);


    TarefasDTO paraTarefaDTO(TarefasEntity entity);


    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);

    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);
}
