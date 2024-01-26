package com.boveda.services;

import com.boveda.models.Credenciales;
import com.boveda.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    public List<Usuario> findByUsuario (String usuario);
}
