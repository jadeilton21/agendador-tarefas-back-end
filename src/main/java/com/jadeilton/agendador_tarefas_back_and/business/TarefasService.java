package com.jadeilton.agendador_tarefas_back_and.business;


import com.jadeilton.agendador_tarefas_back_and.business.dto.TarefasDTO;
import com.jadeilton.agendador_tarefas_back_and.business.mapper.TarefasConverter;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.entity.TarefasEntity;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.enums.StatusNotificacoEnum;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.repository.TarefasRepository;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
