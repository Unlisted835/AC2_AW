package com.example.ac2aw.controllers;

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
import com.example.ac2aw.models.Funcionario;
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
   public void editarFuncionario(@PathVariable int id, @RequestBody FuncionarioCreateUpdateDTO dto) {
      Funcionario f = new Funcionario(dto);
      f = funcionarioService.editar(id, f);
   }

   @GetMapping
   public List<DadosFuncionarioDTO> getCursos() {
      return funcionarioService.obterTodos();
   }

   @GetMapping("/{idFuncionario}")
   public DadosFuncionarioDTO buscarFuncionarioPorId(@PathVariable Integer idFuncionario) {
      return funcionarioService.buscarfuncionarioPorId(idFuncionario);
   }

   @GetMapping("/{idFuncionario}/projetos")
   public List<DadosProjetoDTO> buscarProjetos(@PathVariable Integer idFuncionario) {
      return funcionarioService.buscarProjetos(idFuncionario);
   }

}
