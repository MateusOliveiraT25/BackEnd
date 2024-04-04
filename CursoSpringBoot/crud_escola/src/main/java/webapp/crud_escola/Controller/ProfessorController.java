package webapp.crud_escola.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import webapp.crud_escola.Model.Professor;
import webapp.crud_escola.Repository.ProfessorRepository;
import webapp.crud_escola.Repository.VerificaCadastroProfessorRepository;



@Controller
public class ProfessorController {
    boolean acessoInternoProf = false;
    @Autowired
    private ProfessorRepository ar ;

    @Autowired
    private VerificaCadastroProfessorRepository vcar;

    @PostMapping("/cad-prof")
     public ModelAndView postCadFunc (Professor prof) {
       ModelAndView mv = new ModelAndView("login-prof");
    boolean verificaCpf = vcar.existsById(prof.getCpf());

    if (!verificaCpf) { // Se o CPF não existe, procede com o cadastro
        ar.save(prof);
        mv.addObject("msg", "Cadastro Realizado com sucesso");
    } else {
        mv.addObject("msg", "Cadastro não realizado. CPF já cadastrado.");
    }
    return mv;
}
    


@PostMapping("acesso-prof")
public ModelAndView acessoAdmLogin(@RequestParam String cpf,
        @RequestParam String senha) {
    ModelAndView mv = new ModelAndView();// página interna de acesso

    boolean acessoCPF = cpf.equals(ar.findByCpf(cpf).getCpf());
    boolean acessoSenha = senha.equals(ar.findByCpf(cpf).getSenha());
    if(acessoCPF && acessoSenha){
        String mensagem = "Login Realizado com sucesso";
        System.out.println(mensagem);
        acessoInternoProf = true;
        mv.setViewName("redirect:/interna-prof");
    } else {
        String mensagem = "Login Não Efetuado";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "vermelho");
        mv.setViewName("login-adm");
    }
    return mv;
}



@GetMapping("interna-prof")
public String acessoPageInternaAdm() {
    ModelAndView mv = new ModelAndView();
    String acesso = "";
    if (acessoInternoProf) {
        acesso = "interna-prof";
    } else {
        acesso = "login-prof";
        String mensagem = "Acesso não Permitido - faça Login";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "vermelho");
    }

    return acesso;
}
}

