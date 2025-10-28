package com.example.ac2aw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.example.ac2aw.models.Projeto;

public interface ProjetoService extends ServiceBase<Projeto, Integer> {

   public Projeto findByIdIncludingFuncionarios(@Param("id") Integer id);

   public List<Projeto> findAllInsideAPeriod(@Param("dataInicio") String dataInicio, @Param("dataFim") String dataFim);

}
