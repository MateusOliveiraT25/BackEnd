package webapp.crud_escola.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webapp.crud_escola.Model.Administrador;
import webapp.crud_escola.Repository.AdministradorRepository;
import webapp.crud_escola.Repository.VerificaCadastroAdmRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdministradorController  {
    @Autowired
    private AdministradorRepository ar ;
    @Autowired
    private VerificaCadastroAdmRepository vcar;

    @PostMapping("/cad-adm")
    public ModelAndView postCadAdm (Administrador adm) {
        boolean verificaCpf = vcar.existsById(adm.getCpf());

        ModelAndView mv = new ModelAndView("login-adm");
        if (verificaCpf) {
            ar.save(adm);
            String mensagem = "Cadastro Realizado com sucesso";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
        } else {
            String mensagem = "Cadastro não realizado . . .";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
        }
      
        return mv;
    }

    @PostMapping("/acesso-adm")
public ModelAndView acessoAdmLogin(@RequestParam(required = false) String cpf,
                                    @RequestParam(required = false) String senha) {
    ModelAndView mv = new ModelAndView();

    if (cpf == null || cpf.isEmpty()) {
        String mensagem = "CPF não informado";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.setViewName("login-adm");
        return mv;
    }

    if (senha == null || senha.isEmpty()) {
        String mensagem = "Senha não informada";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.setViewName("login-adm");
        return mv;
    }

    // Verifica se o CPF e a senha correspondem a algum registro de administrador
    boolean acessoCPF = cpf.equals(ar.findByCpf(cpf).getCpf());
    boolean acessoSenha = senha.equals(ar.findByCpf(cpf).getSenha());
    if (acessoSenha && acessoCPF) {
        String mensagem = "Login Realizado com sucesso";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.setViewName("sucesso");
    } else {
        String mensagem = "Login não efetuado";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.setViewName("login-adm");
    }
    
    return mv;
}

}

    
    
