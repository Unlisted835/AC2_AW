package com.example.ac2aw.services;

import java.time.LocalDate;
import java.util.List;

import com.example.ac2aw.models.Projeto;

public interface ProjetoService extends ServiceBase<Projeto, Integer> {

   Projeto getByIdIncludingFuncionarios(int id);

   List<Projeto> listAllInsidePeriod(LocalDate dataInicio, LocalDate dataFim);

   void assignFuncionarioToProjeto(int projetoId, int funcionarioId);

}
