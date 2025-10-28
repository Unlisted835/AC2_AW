package com.example.ac2aw.models;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

   @ManyToOne(mappedBy = "funcionarios")
   private Setor setor;

   @OneToOne(mappedBy = "funcionario")
   private Projeto projeto;

   public Funcionario(String nome) {
      this.nome = nome;
   }
}
