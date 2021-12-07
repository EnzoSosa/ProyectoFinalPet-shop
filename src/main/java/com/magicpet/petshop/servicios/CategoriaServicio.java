package com.magicpet.petshop.servicios;

import com.magicpet.petshop.entidades.Categoria;
import com.magicpet.petshop.errores.ErrorServicio;
import com.magicpet.petshop.repositorios.CategoriaRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServicio {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Transactional
    public void registrarCategoria(Categoria categoria) throws ErrorServicio {
        validar(categoria.getNombre());
        categoriaRepositorio.save(categoria);
    }

    @Transactional
    public void modificarCategoria(Categoria categoria) throws ErrorServicio {
        Optional<Categoria> respuesta = categoriaRepositorio.findById(categoria.getId());
        if (respuesta.isPresent()) {
            validar(categoria.getNombre());
            categoriaRepositorio.save(categoria);
        } else {
            throw new ErrorServicio("La categoria no existe");
        }
    }

    @Transactional
    public void eliminarCategoria(String id) throws ErrorServicio {
        Optional<Categoria> respuesta = categoriaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            categoriaRepositorio.delete(respuesta.get());
        } else {
            throw new ErrorServicio("No se encontró la categoria");
        }

    }

    public void validar(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
    }
}
