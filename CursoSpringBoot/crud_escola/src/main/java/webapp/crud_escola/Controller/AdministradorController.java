package webapp.crud_escola.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webapp.crud_escola.Model.Administrador;
import webapp.crud_escola.Repository.AdministradorRepository;
import webapp.crud_escola.Repository.VerificaCadastroAdmRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdministradorController  {
    boolean acessoInternoAdm = false;
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
           
        }else{
            String mensagem = "Cadastro não realizado . . .";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
        }
      
        return mv;
    }
    @PostMapping("acesso-adm")
    public ModelAndView acessoAdmLogin(@RequestParam String cpf,
                                        @RequestParam String senha) {
        ModelAndView mv = new ModelAndView("interna-adm");
    
        Administrador administrador = ar.findByCpf(cpf);
    
        if (administrador != null && administrador.getSenha().equals(senha)) {
            String mensagem = "Login realizado com sucesso";
            acessoInternoAdm = true;
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
        } else {
            String mensagem = "CPF ou senha incorretos. Login não efetuado";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
        }
    
        return mv;
    }
    

    @GetMapping("/interna-adm")
    public String acessoPageInternaAdm() {
        ModelAndView mv =  new ModelAndView();
        String acesso= "";
        if (acessoInternoAdm) {
            acesso = "interna-adm";
        } else{
            acesso = "login-adm";
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }
        
        return acesso;
    }
    
}