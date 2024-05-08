import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // Lista de 10 disciplinas
        List<String> disciplinas = Arrays.asList(
                "Matemática", "Português", "História", "Geografia",
                "Ciências", "Inglês", "Arte", "Educação Física",
                "Filosofia", "Sociologia"
        );

        model.addAttribute("disciplinas", disciplinas);
        return "cadastro-professor"; // Nome do arquivo HTML do formulário
    }
}
