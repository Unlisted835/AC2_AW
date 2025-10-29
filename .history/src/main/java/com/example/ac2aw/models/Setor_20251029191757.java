package com.example.ac2aw.models;


import java.util.ArrayList;
import java.util.List;

import com.example.ac2aw.dtos.SetorCreateUpdateDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "setores")
@EqualsAndHashCode(of = "id")
public class Setor {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private String nome;

   @OneToMany(mappedBy = "setor", fetch = FetchType.LAZY)
   private List<Funcionario> funcionarios = new ArrayList<>();

   public void setFuncionarios(List<Funcionario> funcionarios) {
      this.funcionarios.clear();
      for (Funcionario funcionario : funcionarios) {
         addFuncionario(funcionario);
      }
   }

   public void addFuncionario(Funcionario funcionario) {
      funcionarios.add(funcionario);
      funcionario.setSetor(this);
   }

   public Setor(String nome) {
      this.nome = nome;
   }

   public Setor(SetorCreateUpdateDTO dto) {
      this.nome = dto.nome();
   }
}
