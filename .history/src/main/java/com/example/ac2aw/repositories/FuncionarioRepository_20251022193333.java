package com.example.ac2aw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ac2aw.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
