package com.example.ac2aw.dtos;

import com.example.ac2aw.models.Funcionario;

public record FuncionarioDTO(Integer id, String nome, SetorDTO setor) {

   public FuncionarioDTO(Funcionario funcionario) {
      this(funcionario.getId(), funcionario.getNome(), new SetorDTO(funcionario.getSetor()));
   }
}
