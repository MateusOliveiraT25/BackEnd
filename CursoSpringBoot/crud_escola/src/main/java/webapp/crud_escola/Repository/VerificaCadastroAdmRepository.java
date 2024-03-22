package webapp.crud_escola.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.crud_escola.Model.VerificaCadastroAdm;


public interface VerificaCadastroAdmRepository extends CrudRepository<VerificaCadastroAdm, String> {
    // Método de busca por CPF
    VerificaCadastroAdm findByCpf(String cpf);
  
    // Método de busca por nome
    VerificaCadastroAdm findByNome(String nome);
}
