package com.magicpet.petshop.controladores;

import com.magicpet.petshop.entidades.Imagen;
import com.magicpet.petshop.entidades.Usuario;
import com.magicpet.petshop.errores.ErrorServicio;
import com.magicpet.petshop.helpers.TimeHelper;
import com.magicpet.petshop.servicios.ImagenServicio;
import com.magicpet.petshop.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.Date;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ImagenServicio imagenServicio;

    private TimeHelper timeHelper;

    @GetMapping("")
    public String getPerfil(RedirectAttributes redirectAttributes, Model model, Principal principal) throws ErrorServicio {
        String username = principal.getName();
        try {
            Usuario usuario = usuarioServicio.obtenerPorUsename(username);
            model.addAttribute("usuario", usuario);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorCodigo", "404");
            redirectAttributes.addFlashAttribute("error", "Hubo un error.");
            return "redirect:/error";
        }
        return "profile/index";
    }

    @GetMapping("/edit")
    public String getEdit(RedirectAttributes redirectAttributes, Model model, Principal principal) {
        String username = principal.getName();
        try {
            Usuario usuario = usuarioServicio.obtenerPorUsename(username);
            model.addAttribute("usuario", usuario);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error intentando modificar tu perfil.");
            return "redirect:/perfil";
        }
        return "profile/edit";
    }

    @PostMapping("/edit")
    public String postEdit(@RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String dni,
            @RequestParam String fechaDeNacimiento,
            @RequestParam MultipartFile archivo,
            Model model,
            RedirectAttributes redirectAttributes,
            Principal principal) {
        try {
            Usuario usuario = usuarioServicio.obtenerPorUsename(principal.getName());

            Date fecha = (Date) timeHelper.covertDate(fechaDeNacimiento);

            if (archivo != null) {
                Imagen imagen = imagenServicio.actualizar(usuario.getId(), archivo);
                usuario.setImagen(imagen);
            }
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setDni(dni);
            usuario.setFechaDeNacimiento(fecha);

            usuarioServicio.modificarPefil(usuario);
            redirectAttributes.addFlashAttribute("success", "Tu perfil se modificó correctamente.");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "profile/edit";
        }

        return "redirect:/profile";
    }

    @GetMapping("/password")
    public String getPassword() {
        return "profile/password";
    }

    @PostMapping("/password")
    public String postPassword(Model model,
            Principal principal,
            RedirectAttributes redirectAttributes,
            @RequestParam String oldPassword, @RequestParam String password, @RequestParam String confirmPassword) {
        String username = principal.getName();
        try {
            Usuario usuario = usuarioServicio.obtenerPorUsename(username);
            usuarioServicio.cambiarPassword(usuario, oldPassword, password, confirmPassword);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "profile/password";
        }

        redirectAttributes.addFlashAttribute("success", "Su contraseña fue modificada correctamente");
        return "redirect:/profile";
    }
}
