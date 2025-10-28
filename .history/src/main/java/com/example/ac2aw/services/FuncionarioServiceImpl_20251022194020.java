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
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'salvar'");
   }

   @Override
   public Funcionario atualizar(Funcionario entity) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
   }

   @Override
   public void deletar(Long id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'deletar'");
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