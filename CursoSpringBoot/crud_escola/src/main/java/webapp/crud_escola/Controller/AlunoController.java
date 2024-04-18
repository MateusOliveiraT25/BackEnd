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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AlunoController {
    boolean acessoInternoAluno = false;


    @Autowired
    private AlunoRepository ar ;
    
    @Autowired
    private VerificaCadastroAlunoRepository vcar;

@PostMapping("cad-aluno")
public ModelAndView postCadAluno(Aluno aluno, @RequestParam("disciplinas") List<String> disciplinasSelecionadas) {
    ModelAndView mv = new ModelAndView("adm/interna-adm");

    // Verifica se o CPF do aluno já está cadastrado
    boolean verificaCpf = vcar.existsById(aluno.getCpf());

    if (!verificaCpf) { // Se o CPF não existe, procede com o cadastro
        // Busca os professores associados às disciplinas selecionadas pelo aluno
        List<Professor> professores = new ArrayList<>();
        for (String disciplinaNome : disciplinasSelecionadas) {
            List<Professor> professoresDaDisciplina = professorRepository.findByDisciplinasNome(disciplinaNome);
            professores.addAll(professoresDaDisciplina);
        }

        if (!professores.isEmpty()) { // Se encontrar professores com as disciplinas selecionadas
            // Por simplicidade, aqui assumimos que o aluno será associado ao primeiro professor encontrado
            Professor professor = professores.get(0);
            aluno.setProfessor(professor);
            ar.save(aluno);
            mv.addObject("msg", "Cadastro realizado com sucesso");
        } else {
            mv.addObject("msg", "Cadastro não realizado. Disciplinas sem professor associado.");
        }
    } else {
        mv.addObject("msg", "Cadastro não realizado. CPF já cadastrado.");
    }

    return mv;
}



    
    
    // No método de postagem
@PostMapping("acesso-aluno")
  public ModelAndView acessoAlunoLogin(@RequestParam String cpf,
          @RequestParam String senha,
          RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/interna-aluno");// página interna de acesso
      try {
          // boolean acessoCPF = cpf.equals(ar.findByCpf(cpf).getCpf());
          boolean acessoCPF = ar.existsById(cpf);
          boolean acessoSenha = senha.equals(ar.findByCpf(cpf).getSenha());

          if (acessoCPF && acessoSenha) {
              String mensagem = "Login Realizado com sucesso";
              System.out.println(mensagem);
              acessoInternoAluno = true;
              mv.addObject("msg", mensagem);
              mv.addObject("classe", "verde");
          } else {
              String mensagem = "Login Não Efetuado";
              System.out.println(mensagem);
              attributes.addFlashAttribute("msg", mensagem);
              attributes.addFlashAttribute("classe", "vermelho");
              mv.setViewName("redirect:/login-aluno");
          }
          
      } catch (Exception e) {
          String mensagem = "Login Não Efetuado";
          System.out.println(mensagem);
          attributes.addFlashAttribute("msg", mensagem);
          attributes.addFlashAttribute("classe", "vermelho");
          mv.setViewName("redirect:/login-aluno");
      }
      return mv;
  }
  

  @GetMapping("/interna-aluno")
  public ModelAndView acessoPageInternaAluno(RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("aluno/interna-aluno");
      if (acessoInternoAluno) {
          System.out.println("Acesso Permitido");
      } else {
          String mensagem = "Acesso não Permitido - faça Login";
          System.out.println(mensagem);
          mv.setViewName("redirect:/login-aluno");
          attributes.addFlashAttribute("msg", mensagem);
          attributes.addFlashAttribute("classe", "vermelho");
      }

      return mv;
  }
}
