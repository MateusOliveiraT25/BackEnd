package webapp.crud_escola.Repository;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import webapp.crud_escola.Model.Disciplina;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {
    List<Disciplina> findByNome(String nome);
}
