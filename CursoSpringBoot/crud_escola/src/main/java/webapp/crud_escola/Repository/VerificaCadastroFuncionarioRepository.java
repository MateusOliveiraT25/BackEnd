package webapp.crud_escola.Repository;
import webapp.crud_escola.Model.VerificaCadastroFuncionario;
import org.springframework.data.repository.CrudRepository;


public interface VerificaCadastroFuncionarioRepository extends CrudRepository<VerificaCadastroFuncionario, String> {
    // Método de busca por CPF
    VerificaCadastroFuncionario findByCpf(String cpf);
  
    // Método de busca por nome
    VerificaCadastroFuncionario findByNome(String nome);
}
