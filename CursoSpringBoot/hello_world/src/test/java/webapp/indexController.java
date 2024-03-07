package webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @RequestMapping("/form")
    public String showForm() {
        return "form"; // retorna o nome do arquivo HTML (sem extensão)
    }

    @PostMapping("/submit")
    public String handleSubmit() {
        return "resultado"; // retorna o nome do arquivo HTML (sem extensão)
    }
}
