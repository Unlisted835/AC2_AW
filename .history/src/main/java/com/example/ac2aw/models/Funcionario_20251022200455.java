package com.example.ac2aw.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "funcionarios")
public class Funcionario {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private String nome;

   @ManyToOne
   @JoinColumn(name = "setor_id")
   private Setor setor;

   @ManyToMany(mappedBy = "funcionario")
   private Projeto projeto;

   public Funcionario(String nome) {
      this.nome = nome;
   }
}
