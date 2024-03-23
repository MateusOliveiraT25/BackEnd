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

    }

