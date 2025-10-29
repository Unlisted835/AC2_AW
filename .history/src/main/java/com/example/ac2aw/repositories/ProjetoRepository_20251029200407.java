package com.example.ac2aw.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.models.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

   @Query("SELECT p FROM Projeto p LEFT JOIN FETCH p.funcionarios WHERE p.id = :id")
   Optional<Projeto> findByIdIncludingFuncionarios(@Param("id") int id);

   @Query("SELECT p FROM Projeto p WHERE p.dataInicio >= :dataInicio AND p.dataFim <= :dataFim")
   List<Projeto> findAllInsidePeriod(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

   @Query("SELECT f FROM Funcionario f WHERE f.id = :id")
   Optional<Funcionario> findFuncionarioById(int id);

   @Query("SELECT p FROM Projeto p WHERE p.id = :id")
   Optional<Projeto> findByIdWithoutFuncionarios(int id);

}
