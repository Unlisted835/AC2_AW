package com.example.ac2aw.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

   @ManyToMany(mappedBy = "funcionarios")
   @JoinColumn(name = "projeto_id")
   private List<Projeto> projeto;

   public Funcionario(String nome) {
      this.nome = nome;
   }
}
