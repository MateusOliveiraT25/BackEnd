import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfessorRepository extends CrudRepository<Professor, String> {
    Professor findByCpf(String cpf);
    Professor findBySenha(String senha);

    @Query("SELECT p FROM Professor p JOIN p.disciplinas d WHERE d.nome = :nomeDisciplina")
    List<Professor> findByDisciplinasNome(@Param("nomeDisciplina") String nomeDisciplina);
}
