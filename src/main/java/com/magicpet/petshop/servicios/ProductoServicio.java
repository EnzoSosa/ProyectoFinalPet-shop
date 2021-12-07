package com.magicpet.petshop.servicios;

import com.magicpet.petshop.entidades.Imagen;
import com.magicpet.petshop.entidades.Producto;
import com.magicpet.petshop.entidades.Usuario;
import com.magicpet.petshop.errores.ErrorServicio;
import com.magicpet.petshop.repositorios.ProductoRepositorio;
import com.magicpet.petshop.repositorios.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void registrarProducto(Producto producto, MultipartFile archivo) throws ErrorServicio {
        
        validarCodigo(producto.getCodigo());
        if (archivo != null) {
            Imagen imagen = imagenServicio.guardar(archivo);
            producto.setImagen(imagen);
        }

        productoRepositorio.save(producto);
    }

    @Transactional
    public void modificarProducto(Producto producto, MultipartFile archivo) throws ErrorServicio {
        Producto respuesta = productoRepositorio.getById(producto.getId());
        if (respuesta != null) {
            validarCodigo(producto.getCodigo());
            if (!archivo.isEmpty()) {
                Imagen imagen = imagenServicio.guardar(archivo);
                producto.setImagen(imagen);
            } else {
                producto.setImagen(respuesta.getImagen());
            }
            productoRepositorio.save(producto);
        } else {
            throw new ErrorServicio("El producto no existe");
        }
    }

    @Transactional
    public void eliminarProducto(String id) throws ErrorServicio {
        Optional<Producto> respuesta = productoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            productoRepositorio.delete(respuesta.get());
        } else {
            throw new ErrorServicio("No se encontró el producto");
        }
    }

    @Transactional
    public List<Producto> findAll() {
        return productoRepositorio.findAll();
    }

    @Transactional
    public Producto getById(String id) {
        return productoRepositorio.getById(id);
    }

    private void validarCodigo(String codigo) throws ErrorServicio {
        if (!codigo.matches("[0-9]{4}")) {
            throw new ErrorServicio("El código debe ser un número de 4 dígitos");
        }
    }

}
