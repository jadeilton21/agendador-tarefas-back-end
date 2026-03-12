package com.jadeilton.agendador_tarefas_back_and.business.mapper;


import com.jadeilton.agendador_tarefas_back_and.business.dto.TarefasDTO;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {



    TarefasEntity paraTarefaEntity(TarefasDTO dto);


    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}
