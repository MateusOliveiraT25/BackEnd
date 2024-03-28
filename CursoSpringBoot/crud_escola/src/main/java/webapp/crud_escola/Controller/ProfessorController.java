package webapp.crud_escola.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import webapp.crud_escola.Model.Professor;
import webapp.crud_escola.Repository.ProfessorRepository;
import webapp.crud_escola.Repository.VerificaCadastroProfessorRepository;



@Controller
public class ProfessorController {
    @Autowired
    private ProfessorRepository ar ;

    @Autowired
    private VerificaCadastroProfessorRepository vcar;

    @PostMapping("/cad-prof")
    public String postCadprof (Professor prof) {
        String cpfVerificar = vcar.findByCpf(prof.getCpf()).getCpf();
        if (cpfVerificar == prof.getCpf()) {
            ar.save(prof);  
        }
       
        return "/login-prof";
    }
    
    
}