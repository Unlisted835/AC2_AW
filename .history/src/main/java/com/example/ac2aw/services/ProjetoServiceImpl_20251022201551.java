package com.example.ac2aw.services;

import java.beans.Transient;
import java.util.List;

import com.example.ac2aw.models.Projeto;
import com.example.ac2aw.repositories.ProjetoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {

   private final ProjetoRepository repository;

   @Override
   @Transactional
   public Projeto save(Projeto entity) {
      return repository.save(entity);
   }

   @Override
   @Transactional
   public Projeto atualizar(Projeto entity) {
      if (!repository.existsById(entity.getId())) {
         throw new EntityNotFoundException("Projeto não encontrado com id: " + entity.getId());
      }
      return repository.save(entity);
   }

   @Override
   @Transactional
   public void deletar(Integer id) {
      if (!repository.existsById(id)) {
         throw new EntityNotFoundException("Projeto não encontrado com id: " + id);
      }
      repository.deleteById(id);
   }

   @Override
   public Projeto buscarPorId(Integer id) {
      return repository.findById(id)
         .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com id: " + id));
   }

   @Override
   public List<Projeto> buscarTodos() {
      return repository.findAll();
   }

}