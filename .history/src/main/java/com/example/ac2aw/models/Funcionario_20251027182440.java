package com.example.ac2aw.models;

import java.util.ArrayList;
import java.util.List;

import com.example.ac2aw.dtos.FuncionarioCreateUpdateDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
   @JoinTable(name = "funcionario_projeto",
         joinColumns = @JoinColumn(name = "funcionario_id"),
         inverseJoinColumns = @JoinColumn(name = "projeto_id")
   )
   private List<Projeto> projetos = new ArrayList<>();

   public void setProjetos(List<Projeto> projetos) {
      this.projetos.clear();
      for (Projeto projeto : projetos) {
         this.addProjeto(projeto);
      }
   }

   public void addProjeto(Projeto projeto) {
      this.projetos.add(projeto);
      if (!projeto.getFuncionarios().contains(this)) {
         projeto.getFuncionarios().add(this);
      }
   }

   public Funcionario(String nome) {
      this.nome = nome;
   }

   public Funcionario(FuncionarioCreateUpdateDTO dto) {
      this.nome = dto.nome();
   }
}
