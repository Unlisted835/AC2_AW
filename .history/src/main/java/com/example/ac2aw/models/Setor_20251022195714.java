package com.example.ac2aw.models;

import java.util.List;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Setor {
   private int id;
   private String nome;

   @OneToMany(mappedBy = "setor")
   private List<Funcionario> funcionarios;

   public Setor(String nome) {
      this.nome = nome;
   }
}
