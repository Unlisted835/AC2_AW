package com.example.ac2aw.services;

import java.util.List;

import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.repositories.FuncionarioRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

   private final FuncionarioRepository repository;

   @Override
   public Funcionario salvar(Funcionario entity) {
      return repository.save(entity);
   }

   @Override
   public Funcionario atualizar(Funcionario entity) {
      return repository.save(entity);
   }

   @Override
   public void deletar(Integer id) {
      repository.deleteById(id);
   }

   @Override
   public Funcionario buscarPorId(Integer id) {
      return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + id));
   }

   @Override
   public List<Funcionario> buscarTodos() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'buscarTodos'");
   }

}