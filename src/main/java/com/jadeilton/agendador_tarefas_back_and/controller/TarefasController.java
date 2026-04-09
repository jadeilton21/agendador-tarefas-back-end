package com.jadeilton.agendador_tarefas_back_and.controller;


import com.jadeilton.agendador_tarefas_back_and.business.TarefasService;
import com.jadeilton.agendador_tarefas_back_and.business.dto.TarefasDTORecord;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.enums.StatusNotificacoEnum;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;
    private final TarefasRepository tarefasRepository;

    @PostMapping
    public ResponseEntity<TarefasDTORecord> gravarTarefas(
            @RequestBody TarefasDTORecord dto,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTORecord>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token) {

        if (dataInicial.isAfter(dataFinal)) {
            throw new IllegalArgumentException("dataInicial não pode ser maior que dataFinal");
        }

        return ResponseEntity.ok(
                tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token)
        );
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTORecord>> buscaTarefasPorEmail(
            @RequestHeader("Authorization") String token) {

        List<TarefasDTORecord> tarefas = tarefasService.buscaTarefasPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token) {

        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTORecord> alteraStatusNotificaco(
            @RequestParam("status") StatusNotificacoEnum status,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                tarefasService.alteraStatus(status, id, token)
        );
    }

    @PutMapping
    public ResponseEntity<TarefasDTORecord> updateTarefas(
            @RequestBody TarefasDTORecord dto,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                tarefasService.updateTarefas(dto, id, token)
        );
    }
}