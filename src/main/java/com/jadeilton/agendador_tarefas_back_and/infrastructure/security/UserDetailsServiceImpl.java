package com.jadeilton.agendador_tarefas_back_and.infrastructure.security;


import com.jadeilton.agendador_tarefas_back_and.business.dto.UsuarioDTO;
import com.jadeilton.agendador_tarefas_back_and.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient client;



    public UserDetails carregaDadosDeUsuario(String emai, String token){
        UsuarioDTO usuarioDTO = client.buscaUsuarioPorEmail(emai,token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
