package com.boveda.services;

import com.boveda.models.Credenciales;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CredencialesService {
    public static String id;
    private CredencialesRepository credencialesRepository;

    public  static List <Credenciales> listaCredenciales = new ArrayList<>();
    @Autowired
    public CredencialesService(CredencialesRepository credencialesRepository){
        this.credencialesRepository=credencialesRepository;
    }

    public void agregarCredenciales(Credenciales credenciales){
        try{
            credencialesRepository.save(credenciales);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("No se pudo guardar el contacto");
        }
    }
    public void borrarCredenciales(Credenciales credenciales){
        try{
            credencialesRepository.delete(credenciales);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("No se pudo eliminar el contacto");
        }
    }

    public Credenciales buscarCredenciales(String plataforma,String id ) {
        Credenciales credenciales = new Credenciales();
        try {
            credenciales=credencialesRepository.findByPlataformaAndAndIdUsuario(plataforma, id);

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("No se encontraron las credenciales ");
            Notification.show("La plataforma no existe en la bóveda");
        }
        return  credenciales;
    }

    public  Credenciales editarCredenciales(TextField nuevousuario, String nuevaclave, Credenciales credenciales){
        String usuario = nuevousuario.getValue();
        try {
            credenciales.setUsuario(usuario);
            credenciales.setClave(nuevaclave);
            Notification.show("Credenciales actualizadas correctamente");
            return credencialesRepository.save(credenciales);
        } catch (Exception ex) {
            ex.printStackTrace();
            Notification.show("La plataforma no existe en la bóveda");
            return  null;
        }


    }

    public List<Credenciales> BuscarCredencialesPorUsuario(String idUsuario){
        List<Credenciales> listaCredenciales= new ArrayList<>();
        try{
          listaCredenciales=credencialesRepository.findByIdUsuario(idUsuario);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("No se pudo guardar el contacto");
        }
        return listaCredenciales;
    }




}
