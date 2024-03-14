package webapp.spring_crud_jpa.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webapp.spring_crud_jpa.Model.Funcionario;
import webapp.spring_crud_jpa.Repositorio.FuncionarioRepositorio;


@Controller
public class FuncionarioController {
    @Autowired
    private FuncionarioRepositorio fr;
    @RequestMapping(value = "/funcionario", method = RequestMethod.GET)
    public String abrirfuncionario() {
        return "funcionario/funcionario";
        }
        @RequestMapping(value = "/funcionario", method = RequestMethod.POST)
        public String gravarfuncionario(Funcionario funcionario) {
        fr.save(funcionario);
        return "redirect:/funcionario";
        }
        }