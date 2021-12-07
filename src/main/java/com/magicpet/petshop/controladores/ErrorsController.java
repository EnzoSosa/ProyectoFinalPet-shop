package com.magicpet.petshop.controladores;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorsController implements ErrorController {
    
    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public String mostrarError(Model model, HttpServletRequest httpServletRequest) {
        String errorMessage = "";
        int codigoError = (int) httpServletRequest.getAttribute("javax.servlet.error.status_code");
        switch (codigoError) {
            case 400:
                errorMessage = "La solicitud es incorrecta.";
                break;
            case 401:
            case 403:
                errorMessage = "No está autorizado para ver este contenido.";
                break;
            case 404:
                errorMessage = "El recurso solicitado no se ha encontrado.";
                break;
            case 500:
                errorMessage = "El servidor no pudo realizar la petición con éxito.";
                break;
            default:
                errorMessage = "Hubo un problema.";
                break;
        }
        model.addAttribute("errorCodigo", "Error [" + codigoError + "]");
        model.addAttribute("error", errorMessage);
        return "error";
    }
}
