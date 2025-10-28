package com.example.ac2aw.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.ac2aw.dtos.ProjetoCreateUpdateDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

   @ManyToMany
   @JoinTable(name = "funcionario_projeto",
      joinColumns = @JoinColumn(name = "projeto_id"),
      inverseJoinColumns = @JoinColumn(name = "funcionario_id")
   )
   private List<Funcionario> funcionarios = new ArrayList<>();

   public void setFuncionarios(List<Funcionario> funcionarios) {
      this.funcionarios.clear();
      for (Funcionario funcionario : funcionarios) {
         addFuncionario(funcionario);
      }
   }

   public void addFuncionario(Funcionario funcionario) {
      funcionarios.add(funcionario);
      if (!funcionario.getProjetos().contains(this)) {
         funcionario.getProjetos().add(this);
      }
   }

   public Projeto(String descricao, LocalDate dataInicio, LocalDate dataFim) {
      this.descricao = descricao;
      this.dataInicio = dataInicio;
      this.dataFim = dataFim;
   }

   public Projeto(ProjetoCreateUpdateDTO dto) {
      this.descricao = dto.descricao();
      this.dataInicio = dto.dataInicio();
      this.dataFim = dto.dataFinal();
   }
}