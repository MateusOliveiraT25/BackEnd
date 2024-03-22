package webapp.crud_escola.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.crud_escola.Model.VerificarCadastroAdm;

public interface VerificaCadastroAdmRepository extends CrudRepository<VerificarCadastroAdm, String> {
    //metodos  busca cpf
    VerificarCadastroAdm findbyCpf(String cpf);
  // metodo busca nome
    VerificarCadastroAdm findbyNome(String nome);
    
}
