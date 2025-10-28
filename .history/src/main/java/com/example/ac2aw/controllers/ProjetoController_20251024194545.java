package com.example.ac2aw.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2aw.dtos.FuncionarioDTO;
import com.example.ac2aw.dtos.ProjetoCreateUpdateDTO;
import com.example.ac2aw.dtos.ProjetoDTO;
import com.example.ac2aw.exceptions.ValidationException;
import com.example.ac2aw.models.Projeto;
import com.example.ac2aw.services.ProjetoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projeto")
@CrossOrigin(allowedHeaders = "*")
@RequiredArgsConstructor
public class ProjetoController {

   private final ProjetoService service;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public ProjetoDTO salvar(@RequestBody ProjetoCreateUpdateDTO dto) {
      if (dto.dataInicio().isAfter(dto.dataFim())) {
         throw new ValidationException("dataFim", "A data de fim não pode ser menor que a data de início");
      }
      Projeto p = new Projeto(dto);
      service.save(p);
      return new ProjetoDTO(p);
   }

   @GetMapping("{id}")
   @ResponseStatus(HttpStatus.OK)
   public ProjetoDTO obterPorId(@PathVariable int id) {
      Projeto p = service.get(id);
      return new ProjetoDTO(p);
   }

   @GetMapping("/{id}/funcionarios")
   @ResponseStatus(HttpStatus.OK)
   public ProjetoDTO buscarIncluindoFuncionarios(@PathVariable int id) {
      Projeto p = service.getByIdIncludingFuncionarios(id);
      return new ProjetoDTO(p);
   }

   @GetMapping
   @ResponseStatus(HttpStatus.OK)
   public List<ProjetoDTO> obterTodos() {
      return service.list().stream().map(ProjetoDTO::new).toList();
   }

   @PutMapping("{id}")
   @ResponseStatus(HttpStatus.OK)
   public ProjetoDTO editProjeto(@PathVariable int id, @RequestBody ProjetoCreateUpdateDTO dto) {
      Projeto p = new Projeto(dto);
      p.setId(id);
      service.edit(p);
      return new ProjetoDTO(p);
   }

   @DeleteMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteProjeto(@PathVariable int id) {
      service.remove(id);
   }

   @PostMapping("/{id}/funcionarios/{funcionarioId}")
   @ResponseStatus(HttpStatus.OK)
   public void vincularFuncionario(
         @PathVariable int id,
         @PathVariable int funcionarioId
   ) {
      service.assignFuncionarioToProjeto(id, funcionarioId);
   }

   @GetMapping("/periodo")
   public List<ProjetoDTO> buscarPorPeriodo(
         @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
         @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim
   ) {
      if (inicio.isAfter(fim)) {
         throw new ValidationException("fim", "A data de fim não pode ser menor que a data de início");
      }
      return service.buscarProjetosPorPeriodo(inicio, fim);
   }

}
