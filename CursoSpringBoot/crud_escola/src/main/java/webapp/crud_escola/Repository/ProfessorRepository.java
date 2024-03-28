package webapp.crud_escola.Repository;
import org.springframework.data.repository.CrudRepository;
import webapp.crud_escola.Model.Professor;
public interface ProfessorRepository extends CrudRepository <Professor, String>{
    
}
