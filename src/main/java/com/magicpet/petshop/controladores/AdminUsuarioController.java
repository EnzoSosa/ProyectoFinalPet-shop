package com.magicpet.petshop.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.magicpet.petshop.entidades.Usuario;
import com.magicpet.petshop.enums.Role;
import com.magicpet.petshop.servicios.UsuarioServicio;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@PreAuthorize("hasRole('ADMIN')") // Si son varios hasAnyRole('ROLE1','ROLE2')
@RequestMapping("/admin/usuarios")
public class AdminUsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("")
    public String getList(Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("usuarios", usuarioServicio.verTodos());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin";
        }
        return "admin/usuario-list";
    }

    @GetMapping("/add")
    public String getAdd(Model model, @ModelAttribute("usuario") Usuario usuario) {
        model.addAttribute("roles", Role.values());
        model.addAttribute("action", "/admin/usuarios/add");
        model.addAttribute("titulo", "Agregar usuario");
        model.addAttribute("password", true);
        return "admin/usuario-form";
    }

    @PostMapping("/add")
    public String postAdd(Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String mail,
            @RequestParam Role rol) {
        
        Usuario usuario =  new Usuario();
        usuario.setUsername(username);
        usuario.setMail(mail);
        usuario.setRol(rol);
        
        try {
            usuario = usuarioServicio.nuevoRegistro(usuario, password, confirmPassword);
            redirectAttributes.addFlashAttribute("success", "Se creó un usuario de manera exitosa.");
            return "redirect:/admin/usuarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("action", "/admin/usuarios/add");
            redirectAttributes.addFlashAttribute("titulo", "Agregar usuario");
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("roles", Role.values());
            redirectAttributes.addFlashAttribute("usuario", usuario);
            redirectAttributes.addFlashAttribute("password", true);
            return "redirect:/admin/usuarios/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable String id, Model model,
            RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            if (usuario == null) {
                redirectAttributes.addFlashAttribute("error", "El usuario solicitado no existe.");
                return "redirect:/admin/usuarios";
            } else {
                model.addAttribute("usuario", usuario);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/usuarios";
        }

        model.addAttribute("action", "/admin/usuarios/edit/" + id);
        model.addAttribute("titulo", "Modificar usuario");
        model.addAttribute("roles", Role.values());
        model.addAttribute("password", false);
        return "admin/usuario-form";
    }

    @PostMapping("/edit/{id}")
    public String postEdit(@PathVariable String id, Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam String username,
            @RequestParam String mail,
            @RequestParam Role rol) {

        Usuario usuario = usuarioServicio.buscarPorId(id);
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "El usuario no existe");
            return "redirect/admin/usuarios";
        }
        try {
            
            usuario.setMail(mail);
            usuario.setUsername(username);
            usuario.setRol(rol);
            usuarioServicio.modificar(usuario);
            redirectAttributes.addFlashAttribute("success", "El usuario se modificó correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("action", "/admin/usuarios/edit/" + id);
            redirectAttributes.addFlashAttribute("roles", Role.values());
            redirectAttributes.addFlashAttribute("usuario", usuario);
            redirectAttributes.addFlashAttribute("password", false);
            return "redirect:/admin/usuarios/edit/" + id;
        }
        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/password/{id}")
    public String getPassword(@PathVariable String id, Model model,
            RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            if (usuario == null) {
                redirectAttributes.addFlashAttribute("error", "El usuario solicitado no existe.");
                return "redirect:/admin/usuarios";
            } else {
                model.addAttribute("id", id);
                model.addAttribute("action", "/admin/usuarios/password/" + id);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/usuarios";
        }
        return "admin/usuario-password-form";
    }

    @PostMapping("/password/{id}")
    public String postPassword(@PathVariable String id, Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam String password,
            @RequestParam String confirmPassword) {
        try {
            usuarioServicio.cambiarPassword(id, password, confirmPassword);
            redirectAttributes.addFlashAttribute("success", "La contraseña se modificó correctamente.");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("id", id);
            model.addAttribute("action", "/admin/usuarios/password/" + id);
            return "admin/usuario-password-form";
        }
        model.addAttribute("action", "/admin/usuarios/password/" + id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);
            usuarioServicio.eliminar(usuario);
            redirectAttributes.addFlashAttribute("success", "El usuario se eliminó correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/rol/{id}")
    public String changeRol(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            usuarioServicio.cambiarRol(id);
            redirectAttributes.addFlashAttribute("success", "Se cambió el rol del usuario correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/activar/{id}")
    public String enableDisable (@PathVariable String id, RedirectAttributes redirectAttributes){
        try {
            usuarioServicio.habilitarDeshabilitar(id);
            redirectAttributes.addFlashAttribute("success", "Se habilitó/deshabilitó del usuario correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }

}
