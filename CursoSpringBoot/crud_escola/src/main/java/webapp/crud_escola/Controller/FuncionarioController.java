package webapp.crud_escola.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import webapp.crud_escola.Model.Funcionario;
import webapp.crud_escola.Repository.FuncionarioRepository;
import webapp.crud_escola.Repository.VerificaCadastroFuncionarioRepository;



@Controller
public class FuncionarioController {
    boolean acessoInternoFunc = false;

    @Autowired
    private FuncionarioRepository ar ;

    @Autowired
    private VerificaCadastroFuncionarioRepository vcar;

    @PostMapping("/cad-func")
     public ModelAndView postCadFunc (Funcionario func) {
       ModelAndView mv = new ModelAndView("login-func");
    boolean verificaCpf = vcar.existsById(func.getCpf());

    if (!verificaCpf) { // Se o CPF não existe, procede com o cadastro
        ar.save(func);
        mv.addObject("msg", "Cadastro Realizado com sucesso");
    } else {
        mv.addObject("msg", "Cadastro não realizado. CPF já cadastrado.");
    }
    return mv;
}
    


@PostMapping("acesso-func")
public ModelAndView acessoFuncLogin(@RequestParam String cpf, @RequestParam String senha) {
    ModelAndView mv = new ModelAndView();
    Funcionario func = ar.findByCpf(cpf);

    if (func != null) {
        boolean acessoCPF = cpf.equals(func.getCpf());
        boolean acessoSenha = senha.equals(func.getSenha());

        if (acessoCPF && acessoSenha) {
            String mensagem = "Login Realizado com sucesso";
            System.out.println(mensagem);
            acessoInternoFunc = true;
            mv.setViewName("redirect:/interna-func");
        } else {
            String mensagem = "Login Não Efetuado";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
            mv.setViewName("login-func");
        }
    } else {
        // Tratamento para CPF não cadastrado
        String mensagem = "CPF não cadastrado ou dados incorretos";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "vermelho");
        mv.setViewName("login-func");
    }

    return mv;
}



@GetMapping("interna-func")
public String acessoPageInternaFunc() {
    ModelAndView mv = new ModelAndView();
    String acesso = "";
    if (acessoInternoFunc) {
        acesso = "interna-func";
    } else {
        acesso = "login-func";
        String mensagem = "Acesso não Permitido - faça Login";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "vermelho");
    }

    return acesso;
}
}
