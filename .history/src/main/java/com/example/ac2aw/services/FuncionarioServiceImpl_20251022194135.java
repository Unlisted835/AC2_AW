package com.example.ac2aw.services;

import java.util.List;

import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.repositories.FuncionarioRepository;

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
   public void deletar(Long id) {
      repository.deleteById(Integer.valueOf(id));
   }

   @Override
   public Funcionario buscarPorId(Long id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
   }

   @Override
   public List<Funcionario> buscarTodos() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'buscarTodos'");
   }

}