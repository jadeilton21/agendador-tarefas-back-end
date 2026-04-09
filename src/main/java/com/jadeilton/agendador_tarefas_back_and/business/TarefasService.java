package com.jadeilton.agendador_tarefas_back_and.business;


import com.jadeilton.agendador_tarefas_back_and.business.dto.TarefasDTORecord;
import com.jadeilton.agendador_tarefas_back_and.business.mapper.TarefaUpdateConverter;
import com.jadeilton.agendador_tarefas_back_and.business.mapper.TarefasConverter;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.entity.TarefasEntity;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.enums.StatusNotificacoEnum;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.exceptions.ResourceNotFoundException;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.repository.TarefasRepository;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {


    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTORecord gravarTarefa(String token, TarefasDTORecord dto){

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        TarefasDTORecord dtoFinal = new TarefasDTORecord(null, dto.nomeTarefa(),
                dto.descricao(),dto.dataCriacao(),email, LocalDateTime.now(), dto.emailUsuario(),
                dto.dataEvento(), StatusNotificacoEnum.PENDENTE);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dtoFinal);

        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }


    public List<TarefasDTORecord> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataIncial, LocalDateTime dataFinal, String token){

        return tarefaConverter.paraListaTarefasDTORecord(
                tarefasRepository.findByDataEventoBetweenAndStatusNotificacoEnum(dataIncial,dataFinal,StatusNotificacoEnum.PENDENTE));

    }




    public List<TarefasDTORecord> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDTORecord(listaTarefas);
    }


    public void deletaTarefaPorId(String id, String token){
        try {
            tarefasRepository.deleteById(id);
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id Inexistente " + id
                    , e.getCause());
        }
    }


    public TarefasDTORecord alteraStatus(StatusNotificacoEnum status, String id, String token){
        try{
            TarefasEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setStatusNotificacoEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        }

        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar Status da tarefa "  + e.getCause());
        }
    }

    public TarefasDTORecord updateTarefas(TarefasDTORecord dto, String id, String token){
        try {
            TarefasEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefaUpdateConverter.updateTarefas(dto, entity);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));

        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar Status da tarefa" + e.getCause());
        }

    }
}