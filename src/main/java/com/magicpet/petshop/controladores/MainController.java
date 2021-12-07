package com.magicpet.petshop.controladores;

import com.magicpet.petshop.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("")
    public String getIndex() {
        return "index";
    }
    
    @GetMapping("/login")
    public String getLogin(@RequestParam(required=false,defaultValue="false") boolean error,
            @RequestParam(required=false,defaultValue="false") boolean register,
            @RequestParam(required=false,defaultValue="false") boolean logout,
            Model model) {
        if (error) {
            model.addAttribute("error", "Hubo un error al intentar conectarse");
        }
        if (logout) {
            model.addAttribute("logout", "Se desconectó correctamente");
        }
        return "login";
    }
    
    @PostMapping("/register")
    public String postRegister(RedirectAttributes redirectAttributes,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String mail) {
        try {
            usuarioServicio.nuevoRegistro(username, password, confirmPassword, mail);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
        redirectAttributes.addFlashAttribute("success", "Se registró correctamente.");
        return "redirect:/login";
    }
}