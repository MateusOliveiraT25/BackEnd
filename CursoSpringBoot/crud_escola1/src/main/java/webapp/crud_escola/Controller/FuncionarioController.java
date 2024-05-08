package webapp.crud_escola.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
  public ModelAndView acessoFuncLogin(@RequestParam String cpf,
          @RequestParam String senha,
          RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/interna-func");// página interna de acesso
      try {
          // boolean acessoCPF = cpf.equals(ar.findByCpf(cpf).getCpf());
          boolean acessoCPF = ar.existsById(cpf);
          boolean acessoSenha = senha.equals(ar.findByCpf(cpf).getSenha());

          if (acessoCPF && acessoSenha) {
              String mensagem = "Login Realizado com sucesso";
              System.out.println(mensagem);
              acessoInternoFunc = true;
              mv.addObject("msg", mensagem);
              mv.addObject("classe", "verde");
          } else {
              String mensagem = "Login Não Efetuado";
              System.out.println(mensagem);
              attributes.addFlashAttribute("msg", mensagem);
              attributes.addFlashAttribute("classe", "vermelho");
              mv.setViewName("redirect:/login-func");
          }
          
      } catch (Exception e) {
          String mensagem = "Login Não Efetuado";
          System.out.println(mensagem);
          attributes.addFlashAttribute("msg", mensagem);
          attributes.addFlashAttribute("classe", "vermelho");
          mv.setViewName("redirect:/login-func");
      }
      return mv;
  }
  

  @GetMapping("/interna-func")
  public ModelAndView acessoPageInternaFunc(RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("interna-func");
      if (acessoInternoFunc) {
          System.out.println("Acesso Permitido");
      } else {
          String mensagem = "Acesso não Permitido - faça Login";
          System.out.println(mensagem);
          mv.setViewName("redirect:/login-func");
          attributes.addFlashAttribute("msg", mensagem);
          attributes.addFlashAttribute("classe", "vermelho");
      }

      return mv;
  }
}

