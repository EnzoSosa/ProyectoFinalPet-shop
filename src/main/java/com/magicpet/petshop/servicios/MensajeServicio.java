package com.magicpet.petshop.servicios;

import com.magicpet.petshop.entidades.Mensaje;
import com.magicpet.petshop.errores.ErrorServicio;
import com.magicpet.petshop.repositorios.MensajeRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensajeServicio {

    @Autowired
    MensajeRepositorio mensajeRepositorio;
    
    public Mensaje nuevoMensaje(Mensaje mensaje) throws ErrorServicio {
        if (mensaje.getMail() == null || mensaje.getMail().isEmpty()) {
            throw new ErrorServicio("El correo no puede estár vacío");
        }
        if (mensaje.getMensaje() == null || mensaje.getMensaje().isEmpty()) {
            throw new ErrorServicio("El mensaje no puede estár vacío");
        }
        return mensajeRepositorio.save(mensaje);
    }
    
    public List<Mensaje> verTodos() throws ErrorServicio {
        List<Mensaje> lista = mensajeRepositorio.findAll();
        if (lista.size() == 0) {
            throw new ErrorServicio("No hay mensajes");
        }
        return lista;
    }
}
