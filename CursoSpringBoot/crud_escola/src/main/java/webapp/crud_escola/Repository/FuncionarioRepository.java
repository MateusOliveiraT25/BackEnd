package webapp.crud_escola.Repository;

import org.springframework.data.repository.CrudRepository;
import webapp.crud_escola.Model.Funcionario;
public interface FuncionarioRepository extends CrudRepository <Funcionario, String>{
    
}
