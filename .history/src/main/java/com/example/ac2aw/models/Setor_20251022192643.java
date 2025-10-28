package com.example.ac2aw.entities;

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

   public Setor(String nome) {
      this.nome = nome;
   }
}
