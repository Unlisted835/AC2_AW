package com.example.ac2aw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ac2aw.dtos.FuncionarioCreateUpdateDTO;
import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.models.Projeto;
import com.example.ac2aw.models.Setor;
import com.example.ac2aw.repositories.FuncionarioRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
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
   public Funcionario edit(Funcionario entity) {
      if (!repository.existsById(entity.getId())) {
         throw new EntityNotFoundException("Funcionário não encontrado com id: " + entity.getId());
      }
      return repository.save(entity);
   }

   @Override
   @Transactional
   public void remove(Integer id) {
      if (!repository.existsById(id)) {
         throw new EntityNotFoundException("Funcionário não encontrado com id: " + id);
      }
      repository.deleteById(id);
   }

   @Override
   public Funcionario get(Integer id) {
      return repository.findById(id)
         .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + id));
   }

   @Override
   public List<Funcionario> list() {
      return repository.findAll();
   }

   @Override
   public List<Projeto> listAllRelatedProjects(int funcionarioId) {
      if (!repository.existsById(funcionarioId)) {
         throw new EntityNotFoundException("Funcionário não encontrado com id: " + funcionarioId);
      }
      return repository.findAllRelatedProjects(funcionarioId);
   }

   @Override
   public Setor findSetorById(int id) {
      Setor setor = repository.findSetorById(dto.setorId())
         .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com id: " + dto.setorId()));
      return new FuncionarioCreateUpdateDTO(dto.nome(), dto.setorId(), setor);
   }

}