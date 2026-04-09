package com.jadeilton.agendador_tarefas_back_and.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.enums.StatusNotificacoEnum;

import java.time.LocalDateTime;

public record TarefasDTORecord(String id,
                               String nomeTarefa,
                               String descricao,
                               LocalDateTime dataCriacao,
                               String email, @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
 LocalDateTime dataEvento,
                               String emailUsuario,
                               LocalDateTime dataAlteracao,
                               StatusNotificacoEnum statusNotificacoEnum) {
}
