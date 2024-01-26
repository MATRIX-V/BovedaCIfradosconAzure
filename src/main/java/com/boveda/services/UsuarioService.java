package com.boveda.services;

import com.boveda.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository=usuarioRepository;
    }


}
