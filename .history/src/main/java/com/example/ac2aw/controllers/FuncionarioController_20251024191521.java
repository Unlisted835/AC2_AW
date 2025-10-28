package com.example.ac2aw.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2aw.dtos.FuncionarioCreateUpdateDTO;
import com.example.ac2aw.dtos.FuncionarioDTO;
import com.example.ac2aw.dtos.ProjetoDTO;
import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.models.Projeto;
import com.example.ac2aw.models.Setor;
import com.example.ac2aw.services.FuncionarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin(allowedHeaders = "*")
@RequiredArgsConstructor
public class FuncionarioController {

   private final FuncionarioService service;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public FuncionarioDTO salvarFuncionario(@RequestBody FuncionarioCreateUpdateDTO dto) {
      Funcionario f = new Funcionario(dto);
      f = service.save(f);
      return new FuncionarioDTO(f);
   }

   @DeleteMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void removerFuncionario(@PathVariable int id) {
      service.remove(id);
   }

   @PutMapping("{id}")
   @ResponseStatus(HttpStatus.OK)
   public FuncionarioDTO editarFuncionario(@PathVariable int id, @RequestBody FuncionarioCreateUpdateDTO dto) {
      Funcionario f = new Funcionario(dto);
      f.setId(id);
      f = service.edit(f);
      return new FuncionarioDTO(f);
   }

   @GetMapping
   @ResponseStatus(HttpStatus.OK)
   public List<FuncionarioDTO> obterTodos() {
      return service.list().stream().map(FuncionarioDTO::new).toList();
   }

   @GetMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   public FuncionarioDTO buscarPorId(@PathVariable int id) {
      Funcionario f = service.get(id);
      return new FuncionarioDTO(f);
   }

   @GetMapping("/{id}/projetos")
   @ResponseStatus(HttpStatus.OK)
   public List<ProjetoDTO> buscarProjetos(@PathVariable int id) {
      List<Projeto> list = service.listAllRelatedProjects(id);
      return list.stream().map(ProjetoDTO::new).toList();
   }

}
