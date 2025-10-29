package com.example.ac2aw.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.models.Projeto;
import com.example.ac2aw.models.Setor;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

	@Query("SELECT p FROM Funcionario f JOIN f.projetos p WHERE f.id = :id")
   List<Projeto> findAllRelatedProjects(@Param("id") int id);

   @Query("SELECT s FROM Setor s WHERE s.id = :setorId")
   Optional<Setor> findSetorById(int setorId);

}
