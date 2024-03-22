package webapp.crud_escola.Model;

import java.io.Serializable;

import jakarta.persistence.Entity;

@Entity
public class VerificarCadastroAdm implements Serializable{
    //atributos
    @id
    private String cpf;
    private String nome;
    //metodos
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
