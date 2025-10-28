package com.example.ac2aw.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.ac2aw.models.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {


   public Optional<Projeto> findByIdIncludingFuncionarios(@Param("id") Integer id);

}
