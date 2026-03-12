package com.jadeilton.agendador_tarefas_back_and;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgendadorTarefasBackAndApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendadorTarefasBackAndApplication.class, args);
	}

}
