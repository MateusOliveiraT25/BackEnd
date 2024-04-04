package webapp.crud_escola.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import webapp.crud_escola.Model.Aluno;
import webapp.crud_escola.Repository.AlunoRepository;
import webapp.crud_escola.Repository.VerificaCadastroAlunoRepository;

@Controller
public class AlunoController {
    boolean acessoInternoAluno = false;


    @Autowired
    private AlunoRepository ar ;
    
    @Autowired
    private VerificaCadastroAlunoRepository vcar;

    @PostMapping("cad-aluno")
    public ModelAndView postCadAluno (Aluno aluno) {
       ModelAndView mv = new ModelAndView("login-aluno");
    boolean verificaCpf = vcar.existsById(aluno.getCpf());

    if (!verificaCpf) { // Se o CPF não existe, procede com o cadastro
        ar.save(aluno);
        mv.addObject("msg", "Cadastro Realizado com sucesso");
    } else {
        mv.addObject("msg", "Cadastro não realizado. CPF já cadastrado.");
    }
    return mv;
}
@PostMapping("acesso-aluno")
public ModelAndView acessoAdmLogin(@RequestParam String cpf, @RequestParam String senha) {
    ModelAndView mv = new ModelAndView();
    Aluno aluno = ar.findByCpf(cpf);

    if (aluno != null) {
        boolean acessoCPF = cpf.equals(adm.getCpf());
        boolean acessoSenha = senha.equals(adm.getSenha());

        if (acessoCPF && acessoSenha) {
            String mensagem = "Login Realizado com sucesso";
            System.out.println(mensagem);
            acessoInternoAluno = true;
            mv.setViewName("redirect:/interna-aluno");
        } else {
            String mensagem = "Login Não Efetuado";
            System.out.println(mensagem);
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
            mv.setViewName("login-aluno");
        }
    } else {
        // Tratamento para CPF não cadastrado
        String mensagem = "CPF não cadastrado ou dados incorretos";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "vermelho");
        mv.setViewName("login-aluno");
    }

    return mv;
}



@GetMapping("interna-aluno")
public String acessoPageInternaAluno() {
    ModelAndView mv = new ModelAndView();
    String acesso = "";
    if (acessoInternoAluno) {
        acesso = "interna-aluno";
    } else {
        acesso = "login-aluno";
        String mensagem = "Acesso não Permitido - faça Login";
        System.out.println(mensagem);
        mv.addObject("msg", mensagem);
        mv.addObject("classe", "vermelho");
    }

    return acesso;
}
}
