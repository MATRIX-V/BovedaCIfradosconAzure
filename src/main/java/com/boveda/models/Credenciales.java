package com.boveda.models;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "credenciales")

public class Credenciales {
    @Id
    private String id;

    private String plataforma;

    private String usuario;
    @Indexed (name="clave", unique = true)
    private String clave;
    private String idUsuario;

    /*public Credenciales(String plataforma, String usuario, String clave, String id) {
        this.plataforma = plataforma;
        this.usuario = usuario;
        this.clave = clave;
    }*/

    public Credenciales(String plataforma, String usuario, String clave, String idUsuario) {

        this.plataforma = plataforma;
        this.usuario = usuario;
        this.clave = clave;
        this.idUsuario = idUsuario;
    }

    public Credenciales() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getidUsuario() {
        return idUsuario;
    }

    public void setidUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
