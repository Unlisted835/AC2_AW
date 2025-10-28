package com.example.ac2aw.dtos;

import com.example.ac2aw.models.Funcionario;

public record FuncionarioDTO(int id, String nome, SetorDTO setor) {

   public FuncionarioDTO(Funcionario funcionario, boolean recursive) {
      this(funcionario.getId(), funcionario.getNome(), recursive ? new SetorDTO(funcionario.getSetor(), false) : null);
   }
}
