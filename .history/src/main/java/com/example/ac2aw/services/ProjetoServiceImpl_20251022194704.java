package com.example.ac2aw.services;

import java.util.List;

import com.example.ac2aw.models.Projeto;
import com.example.ac2aw.repositories.ProjetoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {

   private final ProjetoRepository repository;

   @Override
   public Projeto salvar(Projeto entity) {
      return repository.save(entity);
   }

   @Override
   public Projeto atualizar(Projeto entity) {
      if (!repository.existsById(entity.getId())) {
         throw new EntityNotFoundException("Funcionário não encontrado com id: " + entity.getId());
      }
      return repository.save(entity);
   }

   @Override
   public void deletar(Integer id) {
      if (!repository.existsById(id)) {
         throw new EntityNotFoundException("Funcionário não encontrado com id: " + id);
      }
      repository.deleteById(id);
   }

   @Override
   public Projeto buscarPorId(Integer id) {
      return repository.findById(id)
         .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + id));
   }

   @Override
   public List<Projeto> buscarTodos() {
      return repository.findAll();
   }

}