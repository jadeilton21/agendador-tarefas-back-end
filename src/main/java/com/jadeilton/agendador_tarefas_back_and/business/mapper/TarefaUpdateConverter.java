package com.jadeilton.agendador_tarefas_back_and.business.mapper;


import com.jadeilton.agendador_tarefas_back_and.business.dto.TarefasDTORecord;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {


    void updateTarefas(TarefasDTORecord dto, @MappingTarget TarefasEntity entity);
}
