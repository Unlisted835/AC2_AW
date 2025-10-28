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
   public Projeto edit(Projeto entity) {
      if (!repository.existsById(entity.getId())) {
         throw new EntityNotFoundException("Projeto não encontrado com id: " + entity.getId());
      }
      return repository.save(entity);
   }

   @Override
   @Transactional
   public void remove(Integer id) {
      if (!repository.existsById(id)) {
         throw new EntityNotFoundException("Projeto não encontrado com id: " + id);
      }
      repository.deleteById(id);
   }

   @Override
   public Projeto get(Integer id) {
      return repository.findById(id)
         .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com id: " + id));
   }

   @Override
   public List<Projeto> list() {
      return repository.findAll();
   }

}