package com.example.ac2aw.services;

import java.util.List;

import com.example.ac2aw.models.Setor;
import com.example.ac2aw.repositories.SetorRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetorServiceImpl implements SetorService {

   private final SetorRepository repository;

   @Override
   public Setor salvar(Setor entity) {
      return repository.save(entity);
   }

   @Override
   public Setor atualizar(Setor entity) {
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
   public Setor buscarPorId(Integer id) {
      return repository.findById(id)
         .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + id));
   }

   @Override
   public List<Setor> buscarTodos() {
      return repository.findAll();
   }

}