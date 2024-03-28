package webapp.crud_escola.Repository;
import org.springframework.data.repository.CrudRepository;
import webapp.crud_escola.Model.VerificaCadastroProfessor;


public interface VerificaCadastroProfessorRepository  extends CrudRepository<VerificaCadastroProfessor, String> {
    // Método de busca por CPF
    public VerificaCadastroProfessor findByCpf(String cpf);
  
    // Método de busca por nome
    VerificaCadastroProfessor findByNome(String nome);
}