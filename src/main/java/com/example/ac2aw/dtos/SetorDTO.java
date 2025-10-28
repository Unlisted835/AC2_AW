package com.example.ac2aw.dtos;

import java.util.*;

import com.example.ac2aw.models.Setor;

public record SetorDTO(int id, String nome, List<FuncionarioDTO> funcionarios) {

   public SetorDTO(Setor setor, boolean recursive) {
      this(setor.getId(), setor.getNome(), recursive
            ? setor.getFuncionarios().stream().map(s -> new FuncionarioDTO(s, false)).toList()
            : null);
   }
}