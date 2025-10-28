package com.example.ac2aw.services;

import java.time.LocalDate;
import java.util.List;

import com.example.ac2aw.models.Projeto;

public interface ProjetoService extends ServiceBase<Projeto, Integer> {

   public Projeto getByIdIncludingFuncionarios(int id);

   public List<Projeto> listAllInsidePeriod(LocalDate dataInicio, LocalDate dataFim);

}
