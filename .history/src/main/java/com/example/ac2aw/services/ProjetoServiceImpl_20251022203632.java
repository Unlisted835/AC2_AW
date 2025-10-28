package com.example.ac2aw.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ac2aw.exceptions.ValidationException;
import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.models.Projeto;
import com.example.ac2aw.repositories.ProjetoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
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

   @Override
   public Projeto getByIdIncludingFuncionarios(int id) {
      return repository.findByIdIncludingFuncionarios(id)
         .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com id: " + id));
   }

   @Override
   public List<Projeto> listAllInsidePeriod(LocalDate dataInicio, LocalDate dataFim) {
      if (dataInicio.isAfter(dataFim)) {
         throw new ValidationException("A data de início não pode ser posterior à data de fim.");
      }
      return repository.findAllInsidePeriod(dataInicio, dataFim);
   }

   @Override
   public void assignFuncionarioToProjeto(int projetoId, int funcionarioId) {

      Projeto projeto = repository.findById(projetoId)
         .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com id: " + projetoId));

      Funcionario funcionario = repository.findFuncionarioById(funcionarioId)
         .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + funcionarioId));

      projeto.getFuncionarios().add(funcionario);

      repository.save(projeto);
   }

}