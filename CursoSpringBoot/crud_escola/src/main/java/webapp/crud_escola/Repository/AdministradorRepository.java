package webapp.crud_escola.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.crud_escola.Model.Administrador;



public interface AdministradorRepository extends CrudRepository <Administrador, String>{
    Administrador  findByCpf(String cpf);
    Administrador  findBySenha(String senha);
    
}
