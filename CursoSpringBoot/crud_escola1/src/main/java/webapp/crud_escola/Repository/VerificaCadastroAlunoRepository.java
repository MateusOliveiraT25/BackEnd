package webapp.crud_escola.Repository;
import org.springframework.data.repository.CrudRepository;
import webapp.crud_escola.Model.VerificaCadastroAluno;

public interface VerificaCadastroAlunoRepository extends CrudRepository<VerificaCadastroAluno, String> {
    // Método de busca por CPF
    VerificaCadastroAluno findByCpf(String cpf);
  
    // Método de busca por nome
    VerificaCadastroAluno findByNome(String nome);
}

