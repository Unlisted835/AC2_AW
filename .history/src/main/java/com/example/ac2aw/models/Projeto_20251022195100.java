package com.example.ac2aw.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "projetos")
public class Projeto {
   private int id;
   private String nome;
   private LocalDate dataInicio;
   private LocalDate dataFim;

   @
   private Funcionario funcionario;

   public Projeto(String nome, LocalDate dataInicio, LocalDate dataFim) {
      this.nome = nome;
      this.dataInicio = dataInicio;
      this.dataFim = dataFim;
   }
}
