package com.boveda.services;
import com.boveda.models.Credenciales;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface CredencialesRepository extends MongoRepository<Credenciales, String> {
        public Credenciales findByPlataformaAndAndIdUsuario (String plataforma, String idUsuario);
        public List<Credenciales> findAllByIdUsuario (String idUsuario);
    }
