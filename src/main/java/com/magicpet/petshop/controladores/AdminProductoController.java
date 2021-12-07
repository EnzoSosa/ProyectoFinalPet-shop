package com.magicpet.petshop.controladores;

import java.util.List;

import com.magicpet.petshop.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.magicpet.petshop.entidades.Producto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Enzo sosa
 */
@Controller
@PreAuthorize("hasRole('ADMIN')") // Si son varios hasAnyRole('ROLE1','ROLE2')
@RequestMapping("/admin/productos")
public class AdminProductoController {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("")
    public String vistaListaProductos(Model model, RedirectAttributes redirectAttributes) {
        try {
            List<Producto> productos = productoServicio.findAll();
            model.addAttribute("productos", productos);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin";
        }
        return "admin/producto-list";
    }

    @GetMapping("/add")
    public String adminFormProducto(Model model, @ModelAttribute("producto") Producto producto) {
        
        model.addAttribute("action", "/admin/productos/add");
        model.addAttribute("titulo", "Agregar producto");
        return "admin/producto-form";
    }

    @PostMapping("/add")
    public String adminSaveProducto(Model model, RedirectAttributes redirectAttributes,
            @ModelAttribute("producto") Producto producto,
            MultipartFile archivo) {
        try {
            productoServicio.registrarProducto(producto, archivo);
            redirectAttributes.addFlashAttribute("success", "El producto se agregó exitosamente/correctamente.");
            return "redirect:/admin/productos";
        } catch (Exception e) {
            model.addAttribute("producto", producto);
            model.addAttribute("action", "/admin/productos/add");
            model.addAttribute("titulo", "Agregar producto");
            model.addAttribute("error", e.getMessage());
            return "admin/producto-form";
        }
    }

    @GetMapping("/edit/{id_producto}")
    public String adminEditProducto(@PathVariable String id_producto, Model model,
            RedirectAttributes redirectAttributes) {
        try {
            Producto producto = productoServicio.getById(id_producto);
            if (producto == null) {
                redirectAttributes.addFlashAttribute("error", "El producto solicitado no existe");
                return "redirect:/admin/productos";
            }
            model.addAttribute("producto", producto);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/productos";
        }

        model.addAttribute("action", "/admin/productos/edit/" + id_producto);
        model.addAttribute("titulo", "Modificar producto");
        return "admin/producto-form";
    }

    @PostMapping("/edit/{id_producto}")
    public String adminEditProducto(@PathVariable String id_producto, Model model,
            RedirectAttributes redirectAttributes,
            @ModelAttribute("producto") Producto producto,
            MultipartFile archivo) {
        try {
            productoServicio.modificarProducto(producto, archivo);
            redirectAttributes.addFlashAttribute("success", "El producto se modificó correctamente");
        } catch (Exception e) {
            model.addAttribute("titulo", "Modificar producto");
            model.addAttribute("error", e.getMessage());
            model.addAttribute("producto", producto);
            model.addAttribute("action", "/admin/productos/edit/" + id_producto);
            return "admin/producto-form";
        }
        return "redirect:/admin/productos";
    }

    @GetMapping("/delete/{id_producto}")
    public String adminDeleteProducto(@PathVariable String id_producto, RedirectAttributes redirectAttributes) {
        try {
            productoServicio.eliminarProducto(id_producto);
            redirectAttributes.addFlashAttribute("success", "El producto se eliminó correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/productos";
    }

}
