package com.example.ac2aw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.models.Projeto;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

   @Query("SELECT p FROM Projeto p WHERE p.funcionario.id = :funcionarioId")
   public List<Projeto> findAllRelatedProjects(Integer funcionarioId);

}
