package com.example.ac2aw.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(of = "id")
public class Projeto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private String descricao;
   private LocalDate dataInicio;
   private LocalDate dataFim;

   @ManyToMany (mappedBy = "projetos")
   @JoinColumn(name = "funcionario_id")
   private List<Funcionario> funcionarios = new ArrayList<>();

   public Projeto(String descricao, LocalDate dataInicio, LocalDate dataFim) {
      this.descricao = descricao;
      this.dataInicio = dataInicio;
      this.dataFim = dataFim;
   }
}