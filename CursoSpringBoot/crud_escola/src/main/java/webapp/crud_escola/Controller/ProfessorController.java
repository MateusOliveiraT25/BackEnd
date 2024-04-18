package webapp.crud_escola.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webapp.crud_escola.Model.Disciplina;
import webapp.crud_escola.Model.Professor;
import webapp.crud_escola.Repository.DisciplinaRepository;
import webapp.crud_escola.Repository.ProfessorRepository;
import webapp.crud_escola.Repository.VerificaCadastroProfessorRepository;
import java.util.List;



@Controller
public class ProfessorController {
    boolean acessoInternoProf = false;
    @Autowired
    private ProfessorRepository ar ;

    @Autowired
    private DisciplinaRepository dr ;


    @Autowired
    private VerificaCadastroProfessorRepository vcar;

@PostMapping("/cad-prof")
public ModelAndView postCadProf(Professor prof) {
    ModelAndView mv = new ModelAndView("adm/interna-adm");

    // Verifica se o CPF já está cadastrado
    boolean verificaCpf = vcar.existsById(prof.getCpf());

    // Verifica se a disciplina está preenchida
    boolean disciplinaPreenchida = prof.getDisciplina() != null && !prof.getDisciplina().isEmpty();

    if (!verificaCpf && disciplinaPreenchida) { // Se o CPF não existe e a disciplina está preenchida, procede com o cadastro
        ar.save(prof);
        mv.addObject("msg", "Cadastro Realizado com sucesso");
    } else {
        if (verificaCpf) {
            mv.addObject("msg", "Cadastro não realizado. CPF já cadastrado.");
        } else {
            mv.addObject("msg", "Cadastro não realizado. Disciplina não informada.");
        }
    }

    return mv;
}



@PostMapping("acesso-prof")
  public ModelAndView acessoProfLogin(@RequestParam String cpf,
          @RequestParam String senha,
          RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/interna-prof");// página interna de acesso
      try {
          // boolean acessoCPF = cpf.equals(ar.findByCpf(cpf).getCpf());
          boolean acessoCPF = ar.existsById(cpf);
          boolean acessoSenha = senha.equals(ar.findByCpf(cpf).getSenha());

          if (acessoCPF && acessoSenha) {
              String mensagem = "Login Realizado com sucesso";
              System.out.println(mensagem);
              acessoInternoProf = true;
              mv.addObject("msg", mensagem);
              mv.addObject("classe", "verde");
          } else {
              String mensagem = "Login Não Efetuado";
              System.out.println(mensagem);
              attributes.addFlashAttribute("msg", mensagem);
              attributes.addFlashAttribute("classe", "vermelho");
              mv.setViewName("redirect:/login-prof");
          }
          
      } catch (Exception e) {
          String mensagem = "Login Não Efetuado";
          System.out.println(mensagem);
          attributes.addFlashAttribute("msg", mensagem);
          attributes.addFlashAttribute("classe", "vermelho");
          mv.setViewName("redirect:/login-prof");
      }
      return mv;
  }
  

  @GetMapping("/interna-prof")
  public ModelAndView acessoPageInternaProf(RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("prof/interna-prof");
      if (acessoInternoProf) {
          System.out.println("Acesso Permitido");
      } else {
          String mensagem = "Acesso não Permitido - faça Login";
          System.out.println(mensagem);
          mv.setViewName("redirect:/login-prof");
          attributes.addFlashAttribute("msg", mensagem);
          attributes.addFlashAttribute("classe", "vermelho");
      }

      return mv;
  }




}

