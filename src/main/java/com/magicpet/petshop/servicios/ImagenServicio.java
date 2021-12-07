package com.magicpet.petshop.servicios;

import com.magicpet.petshop.entidades.Imagen;
import com.magicpet.petshop.errores.ErrorServicio;
import com.magicpet.petshop.repositorios.ImagenRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServicio {
    
    @Autowired
    private ImagenRepositorio imagenRepositorio;
    
    @Transactional
    public Imagen guardar(MultipartFile archivo) throws ErrorServicio {
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                
                return imagenRepositorio.save(imagen);
            } catch (Exception e) {
                throw new ErrorServicio("Hubo un error al almacenar la imagen.");
            }
        }
        return null;
    }
    
    public Imagen actualizar(String id, MultipartFile archivo) throws ErrorServicio {
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                
                if (id != null) {
                    Optional<Imagen> respuesta = imagenRepositorio.findById(id);
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }

                    imagen.setMime(archivo.getContentType());
                    imagen.setNombre(archivo.getName());
                    imagen.setContenido(archivo.getBytes());

                    return imagenRepositorio.save(imagen);
                }
            } catch (Exception e) {
                throw new ErrorServicio("No se pudo actualizar la imagen.");
            }
        }
        return null;
    }

}
