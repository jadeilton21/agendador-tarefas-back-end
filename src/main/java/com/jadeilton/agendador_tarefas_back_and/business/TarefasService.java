package com.jadeilton.agendador_tarefas_back_and.business;


import com.jadeilton.agendador_tarefas_back_and.business.dto.TarefasDTO;
import com.jadeilton.agendador_tarefas_back_and.business.mapper.TarefasConverter;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.entity.TarefasEntity;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.enums.StatusNotificacoEnum;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.repository.TarefasRepository;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {


    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto){

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacoEnum(StatusNotificacoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);

        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }


    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataIncial, LocalDateTime dataFinal){

        return tarefaConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetween(dataIncial,dataFinal));

    }




    public List<TarefasDTO> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDTO(listaTarefas);
    }


}
