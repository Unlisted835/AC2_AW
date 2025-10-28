package com.example.ac2aw.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Funcionario {
   private int id;
   private String nome;

   public Funcionario(String nome) {
      this.nome = nome;
   }
   @Override
   public String toString() {
      return "Funcionario{id=" + id + ", nome='" + nome + "'}";
   }
}
