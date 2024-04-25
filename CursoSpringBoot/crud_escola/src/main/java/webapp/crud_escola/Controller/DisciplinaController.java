package webapp.crud_escola.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import webapp.crud_escola.Model.Disciplina;
import webapp.crud_escola.Model.Professor;
import webapp.crud_escola.Repository.DisciplinaRepository;
import webapp.crud_escola.Repository.ProfessorRepository;


@Controller
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/associar-disciplina-professor")
public ModelAndView exibirFormularioCadastro() {
    ModelAndView mv = new ModelAndView("disciplina");
    Iterable<Professor> professoresIterable = professorRepository.findAll();
    List<Professor> professores = new ArrayList<>();
    professoresIterable.forEach(professores::add);
    mv.addObject("professores", professores);
    return mv;
}

@PostMapping("/cadastrar-disciplina")
public ModelAndView cadastrarDisciplina(@RequestParam String nome, @RequestParam String professorCpf) {
    ModelAndView mv = new ModelAndView("redirect:/cadastro-disciplina");
    Professor professor = professorRepository.findByCpf(professorCpf);
    if (professor != null) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        
        // Verifica se a lista de professores está inicializada
        if (disciplina.getProfessores() == null) {
            disciplina.setProfessores(new ArrayList<>());
        }
        
        disciplina.getProfessores().add(professor);
        disciplinaRepository.save(disciplina);
        mv.addObject("msg", "Disciplina cadastrada com sucesso!");
    } else {
        mv.addObject("msg", "Professor não encontrado. Cadastre o professor antes de associá-lo à disciplina.");
    }
    return mv;
}


}
