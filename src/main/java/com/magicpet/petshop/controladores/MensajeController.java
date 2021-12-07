package com.magicpet.petshop.controladores;

import com.magicpet.petshop.entidades.Mensaje;
import com.magicpet.petshop.servicios.MensajeServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mensaje")
public class MensajeController {
    
    @Autowired
    MensajeServicio mensajeServicio;
    
    @PostMapping("")
    public String enviarMensaje(@ModelAttribute("mensaje") Mensaje mensaje, RedirectAttributes redirectAttributes) {
        try {
            mensajeServicio.nuevoMensaje(mensaje);
            redirectAttributes.addFlashAttribute("success", "Gracias por enviar su mensaje! 💘");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } finally {
            return "redirect:/#contacto";
        }
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ver")
    public String verMensajes(RedirectAttributes redirectAttributes, Model model) {
        try {
            List<Mensaje> lista = mensajeServicio.verTodos();
            model.addAttribute("mensajes", lista);
        } catch(Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/mensaje/ver";
        }
        return "admin/mensaje-list";
    }
}
