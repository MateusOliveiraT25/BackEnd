package webapp.crud_escola.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import webapp.crud_escola.Model.Funcionario;
import webapp.crud_escola.Repository.FuncionarioRepository;
import webapp.crud_escola.Repository.VerificaCadastroFuncionarioRepository;



@Controller
public class FuncionarioController {
    

    private FuncionarioRepository ar ;

    @Autowired
    private VerificaCadastroFuncionarioRepository vcar;

    @PostMapping("/cad-func")
    public String postCadprof (Funcionario func) {
        String cpfVerificar = vcar.findByCpf(func.getCpf()).getCpf();
        if (cpfVerificar == func.getCpf()) {
            ar.save(func);  
        }
       
        return "/login-func";
    }
    
    
}
