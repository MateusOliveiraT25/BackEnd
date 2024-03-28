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
         ModelAndView mv = new ModelAndView("login-adm");
         return mv;
     }
     @GetMapping("/cad-adm")
     public ModelAndView cadAdm() {
         ModelAndView mv = new ModelAndView("cad-adm");
         return mv;
     }

     @GetMapping("/login-prof")
     public ModelAndView abrirLoginProfessor() {
         ModelAndView mv = new ModelAndView("login-prof");
         return mv;
     }

     @GetMapping("/cad-prof")
     public ModelAndView cadProfessor() {
         ModelAndView mv = new ModelAndView("cad-prof");
         return mv;
     }

     @GetMapping("/login-func")
     public ModelAndView abrirLoginFuncionario() {
         ModelAndView mv = new ModelAndView("login-func");
         return mv;
     }

     @GetMapping("/cad-func")
     public ModelAndView cadFuncionario() {
         ModelAndView mv = new ModelAndView("cad-func");
         return mv;
     }

     @GetMapping("/login-aluno")
     public ModelAndView abrirLoginAluno() {
         ModelAndView mv = new ModelAndView("login-aluno");
         return mv;
     }

     @GetMapping("/cad-aluno")
     public ModelAndView cadAluno() {
         ModelAndView mv = new ModelAndView("cad-aluno");
         return mv;
     }
    }

