package com.example.ac2aw.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Funcionario {
   private int id;
   private String nome;

   public Funcionario(String nome) {
      this.nome = nome;
   }
}
