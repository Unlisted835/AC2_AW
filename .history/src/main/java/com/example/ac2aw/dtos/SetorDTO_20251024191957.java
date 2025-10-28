package com.example.ac2aw.dtos;

import java.util.List;

import com.example.ac2aw.models.Setor;

public record SetorDTO(Integer id, String nome, List<FuncionarioDTO> funcionarios) {

   public SetorDTO(Setor setor) {
      this(setor.getId(), setor.getNome(), setor.getFuncionarios().stream().map(FuncionarioDTO::new).toList());
   }
}