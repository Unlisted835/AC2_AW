package com.example.ac2aw.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ac2aw.models.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

   @Query("SELECT p FROM Projeto p LEFT JOIN FETCH p.funcionario WHERE p.id = :id")
   public Optional<Projeto> findByIdIncludingFuncionarios(@Param("id") Integer id);

   @Query("SELECT p FROM Projeto p WHERE p.dataInicio >= :dataInicio AND p.dataFim <= :dataFim")
   public List<Projeto> findAllInsideAPeriod(@Param("dataInicio") String dataInicio, @Param("dataFim") String dataFim);
   
}
