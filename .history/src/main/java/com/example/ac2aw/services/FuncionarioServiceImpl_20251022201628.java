package com.example.ac2aw.services;

import java.util.List;

import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.models.Projeto;
import com.example.ac2aw.repositories.FuncionarioRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

   private final FuncionarioRepository repository;

   @Override
   @Transactional
   public Funcionario save(Funcionario entity) {
      return repository.save(entity);
   }

   @Override
   @Transactional
   public Funcionario update(Funcionario entity) {
      if (!repository.existsById(entity.getId())) {
         throw new EntityNotFoundException("Funcionário não encontrado com id: " + entity.getId());
      }
      return repository.save(entity);
   }

   @Override
   @Transactional
   public void deletar(Integer id) {
      if (!repository.existsById(id)) {
         throw new EntityNotFoundException("Funcionário não encontrado com id: " + id);
      }
      repository.deleteById(id);
   }

   @Override
   public Funcionario buscarPorId(Integer id) {
      return repository.findById(id)
         .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + id));
   }

   @Override
   public List<Funcionario> buscarTodos() {
      return repository.findAll();
   }

   @Override
   public List<Projeto> findAllRelatedProjects(Integer funcionarioId) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'findAllRelatedProjects'");
   }

}