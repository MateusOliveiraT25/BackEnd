package webapp.crud_escola.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import webapp.crud_escola.Model.Administrador;
import webapp.crud_escola.Repository.AdministradorRepository;
import webapp.crud_escola.Repository.VerificaCadastroAdmRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdministradorController  {
    @Autowired
    private AdministradorRepository ar ;
    @Autowired
    private VerificaCadastroAdmRepository vcar;


@PostMapping("cad-adm")
public ModelAndView postCadAdm(Administrador adm) {
    ModelAndView mv = new ModelAndView("login-adm");
    boolean verificaCpf = vcar.existsById(adm.getCpf());

    if (!verificaCpf) { // Se o CPF não existe, procede com o cadastro
        ar.save(adm);
        mv.addObject("msg", "Cadastro Realizado com sucesso");
    } else {
        mv.addObject("msg", "Cadastro não realizado. CPF já cadastrado.");
    }
    return mv;
}
@PostMapping("acesso-adm")
public ModelAndView acessoAdmLogin(@RequestParam String cpf, @RequestParam String senha, HttpServletRequest request) {
    ModelAndView mv = new ModelAndView();
    Administrador adm = ar.findByCpf(cpf);

    if (adm != null) {
        boolean acessoCPF = cpf.equals(adm.getCpf());
        boolean acessoSenha = senha.equals(adm.getSenha());

        if (acessoCPF && acessoSenha) {
            String mensagem = "Login Realizado com sucesso";
            System.out.println(mensagem);

            // Adicionar usuário à sessão
            HttpSession session = request.getSession();
            session.setAttribute("usuario_nome", adm.getNome());
            session.setAttribute("usuario_cpf", adm.getCpf());

            mv.setViewName("redirect:/interna-adm");
        } else {
            String mensagem = "Login Não Efetuado";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
            mv.setViewName("login-adm");
        }
    } else {
        // Tratamento para CPF não cadastrado
        String mensagem = "CPF não cadastrado ou dados incorretos";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "vermelho");
        mv.setViewName("login-adm");
    }

    return mv;
}

@GetMapping("interna-adm")
public String acessoPageInternaAdm(HttpServletRequest request) {
    ModelAndView mv = new ModelAndView();
    HttpSession session = request.getSession();
    boolean acessoInternoAdm = session.getAttribute("usuario_cpf") != null;

    String acesso = "";
    if (acessoInternoAdm) {
        acesso = "interna-adm";
    } else {
        acesso = "login-adm";
        String mensagem = "Acesso não Permitido - faça Login";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "vermelho");
    }

    return acesso;
}
}
