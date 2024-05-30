package br.com.mateus.apirest_senai.Model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Setter
@Getter

public class Responsavel implements Serializable{
    //atributos
    @Id
    private Long id;
    private String nome;


}
