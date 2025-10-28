package com.example.ac2aw.dtos;

import java.time.LocalDate;
import java.util.List;

import com.example.ac2aw.models.Projeto;

public record ProjetoDTO(
      int id,
      String descricao,
      LocalDate dataInicio,
      LocalDate dataFinal,
      List<FuncionarioDTO> funcionarios) {

   public ProjetoDTO(Projeto projeto) {
      this(
         projeto.getId(), 
         projeto.getDescricao(), 
         projeto.getDataInicio(), 
         projeto.getDataFim(),
         projeto.getFuncionarios().stream().map(FuncionarioDTO::new).toList());
   }

}
