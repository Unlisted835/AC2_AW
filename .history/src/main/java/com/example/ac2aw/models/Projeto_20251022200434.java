package com.example.ac2aw.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private String nome;
   private LocalDate dataInicio;
   private LocalDate dataFim;

   @ManyToMany (mappedBy = "projeto")
   private List<Funcionario> funcionario;

   public Projeto(String nome, LocalDate dataInicio, LocalDate dataFim) {
      this.nome = nome;
      this.dataInicio = dataInicio;
      this.dataFim = dataFim;
   }
}
