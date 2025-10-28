package com.example.ac2aw.dtos;

import java.time.LocalDate;

import com.example.ac2aw.models.Projeto;


public record ProjetoDTO(Integer id, String descricao,LocalDate dataInicio, LocalDate dataFinal) {

 public ProjetoDTO(Projeto projeto){
    this(projeto.getId(), projeto.getDescricao(), projeto.getDataInicio(), projeto.getDataFim());
 }

}
