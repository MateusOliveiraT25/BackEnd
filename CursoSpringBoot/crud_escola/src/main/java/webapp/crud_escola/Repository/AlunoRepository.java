package webapp.crud_escola.Repository;
import org.springframework.data.repository.CrudRepository;
import webapp.crud_escola.Model.Aluno;

public interface AlunoRepository extends CrudRepository <Aluno, String>{
    
}

