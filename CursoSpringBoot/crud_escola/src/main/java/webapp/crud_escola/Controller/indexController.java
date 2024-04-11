package webapp.crud_escola.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class indexController {
     //método
    @GetMapping("/")
    public ModelAndView abrirIndex() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @GetMapping("/home")
    public ModelAndView homeIndex() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/contato")
    public ModelAndView PageContato() {
        ModelAndView mv = new ModelAndView("contato");
        return mv;
    }
     //método
     @GetMapping("/login-adm")
     public ModelAndView abrirLoginAdm() {
         ModelAndView mv = new ModelAndView("adm/login-adm");
         return mv;
     }
     @GetMapping("/cadastro-docente")
     public ModelAndView cadDocente() {
         ModelAndView mv = new ModelAndView("adm/cadastro-docente");
         return mv;
     }


     @GetMapping("/cad-adm")
     public ModelAndView cadAdm() {
         ModelAndView mv = new ModelAndView("adm/cad-adm");
         return mv;
     }
     
     @GetMapping("login-prof")
     public ModelAndView abrirLoginProfessor() {
         ModelAndView mv = new ModelAndView("prof/login-prof");
         return mv;
     }

     @GetMapping("/cad-prof")
     public ModelAndView cadProfessor() {
         ModelAndView mv = new ModelAndView("prof/cad-prof");
         return mv;
     }

     @GetMapping("/login-func")
     public ModelAndView abrirLoginFuncionario() {
         ModelAndView mv = new ModelAndView("func/login-func");
         return mv;
     }

     @GetMapping("/cad-func")
     public ModelAndView cadFuncionario() {
         ModelAndView mv = new ModelAndView("func/cad-func");
         return mv;
     }

     @GetMapping("/login-aluno")
     public ModelAndView abrirLoginAluno() {
         ModelAndView mv = new ModelAndView("aluno/login-aluno");
         return mv;
     }

     @GetMapping("/cad-aluno")
     public ModelAndView cadAluno() {
         ModelAndView mv = new ModelAndView("aluno/cad-aluno");
         return mv;
     }
    }

