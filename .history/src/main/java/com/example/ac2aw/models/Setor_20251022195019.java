package com.example.ac2aw.models;

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

   private List<Funcionario> funcionarios;

   public Setor(String nome) {
      this.nome = nome;
   }
}
