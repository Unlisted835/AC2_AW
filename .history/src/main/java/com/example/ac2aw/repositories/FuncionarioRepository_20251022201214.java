package com.example.ac2aw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.models.Projeto;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

   public List<Projeto> findAllRelatedProjects(Integer funcionarioId);

}
