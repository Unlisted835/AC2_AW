package com.example.ac2aw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ac2aw.models.Setor;
import com.example.ac2aw.repositories.SetorRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SetorServiceImpl implements SetorService {

   private final SetorRepository repository;

   @Override
   @Transactional
   public Setor save(Setor entity) {
      return repository.save(entity);
   }

   @Override
   @Transactional
   public Setor edit(Setor entity) {
      if (!repository.existsById(entity.getId())) {
         throw new EntityNotFoundException("Setor não encontrado com id: " + entity.getId());
      }
      return repository.save(entity);
   }

   @Override
   @Transactional
   public void remove(Integer id) {
      if (!repository.existsById(id)) {
         throw new EntityNotFoundException("Setor não encontrado com id: " + id);
      }
      repository.deleteById(id);
   }

   @Override
   public Setor get(Integer id) {
      return repository.findById(id)
         .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com id: " + id));
   }

   @Override
   public List<Setor> list() {
      return repository.findAll();
   }

   @Override
   public List<Setor> listAllIncludingFuncionarios() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'listAllIncludingFuncionarios'");
   }

}