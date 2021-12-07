package com.magicpet.petshop.controladores;

import com.magicpet.petshop.entidades.Producto;
import com.magicpet.petshop.entidades.Usuario;
import com.magicpet.petshop.errores.ErrorServicio;
import com.magicpet.petshop.servicios.ProductoServicio;
import com.magicpet.petshop.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")
public class ImagenController {

    @Autowired
    private ProductoServicio productoServicio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/producto/{id}")
    public ResponseEntity<byte[]> getByProducto(@PathVariable String id) throws ErrorServicio {
        try {
            Producto producto = productoServicio.getById(id);
            if (producto.getImagen() == null) {
                throw new ErrorServicio("El producto no tiene una imagen.");
            }
            byte[] imagen = producto.getImagen().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity(imagen, headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new ErrorServicio("Hubo un problema al cargar la imagen del producto");
        }   
    }
    
    @GetMapping("/usuario/{id}")
    public ResponseEntity<byte[]> getByUsuario(@PathVariable String id) throws ErrorServicio {
        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            if (usuario.getImagen() == null) {
                throw new ErrorServicio("El usuario no tiene una imagen.");
            }
            byte[] imagen = usuario.getImagen().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity(imagen, headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new ErrorServicio("Hubo un problema al cargar la imagen del usuario");
        }   
    }
}

