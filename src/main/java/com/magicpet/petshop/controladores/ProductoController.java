package com.magicpet.petshop.controladores;

import com.magicpet.petshop.entidades.Producto;
import com.magicpet.petshop.entidades.Usuario;
import com.magicpet.petshop.errores.ErrorServicio;
import com.magicpet.petshop.servicios.ProductoServicio;
import com.magicpet.petshop.servicios.UsuarioServicio;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Enzo sosa
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("")
    private String listaProducto(Model model) {
        model.addAttribute("productos", productoServicio.findAll());
        return "catalogo";
    }

    @GetMapping("/{id_producto}")
    public String vistaIdProducto(@PathVariable String id_producto, Model model) {
        model.addAttribute("producto", productoServicio.getById(id_producto));
        return "producto";
    }

    @GetMapping("favorite/{id_producto}")
    public String agregarQuitarFavorito(@PathVariable String id_producto, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = usuarioServicio.obtenerPorUsename(principal.getName());
            Producto producto = productoServicio.getById(id_producto);
            if (usuario.getFavoritos().contains(producto)) {
                usuario.getFavoritos().remove(producto);
            } else {
                usuario.getFavoritos().add(producto);
            }
            usuarioServicio.modificar(usuario);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/productos";
        }
        return "redirect:/productos";
    }

}
