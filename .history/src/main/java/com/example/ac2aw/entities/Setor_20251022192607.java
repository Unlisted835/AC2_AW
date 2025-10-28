package com.example.ac2aw.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Setor {
   private int id;
   private String nome;

   public Setor(String nome) {
      this.nome = nome;
   }
   @Override
   public String toString() {
      return "Setor{id=" + id + ", nome='" + nome + "'}";
   }
}
