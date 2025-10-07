package ort.da.mvc.facturas.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para manejar la página de inicio
 * Redirige automáticamente a index.html cuando se accede a la raíz del sitio
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }
}