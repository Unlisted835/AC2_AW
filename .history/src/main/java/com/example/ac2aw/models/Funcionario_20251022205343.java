package com.example.ac2aw.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "funcionarios")
@EqualsAndHashCode(of = "id")
public class Funcionario {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private String nome;

   @ManyToOne
   @JoinColumn(name = "setor_id")
   private Setor setor;

   @ManyToMany
   @JoinTable(name = "funcionario_projeto", // Custom join table name
         joinColumns = @JoinColumn(name = "funcionario_id"), // Column in join table referencing Funcionario
         inverseJoinColumns = @JoinColumn(name = "projeto_id") // Column in join table referencing Projeto
   )
   private List<Projeto> projetos = new ArrayList<>();

   public Funcionario(String nome) {
      this.nome = nome;
   }

   public void addProjeto(Projeto projeto) {
      if (this.projetos == null) {
         this.projetos = new ArrayList<>();
      }
      this.projetos.add(projeto);
      if (projeto.getFuncionarios() == null) {
         projeto.setFuncionarios(new ArrayList<>());
      }
      if (!projeto.getFuncionarios().contains(this)) {
         projeto.getFuncionarios().add(this);
      }
   }
}
