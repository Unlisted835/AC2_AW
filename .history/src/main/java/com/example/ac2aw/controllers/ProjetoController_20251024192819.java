package com.example.ac2aw.controllers;

import java.time.LocalDate;

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
   public Projeto salvar(@RequestBody ProjetoDTO projetoDTO) {
      Projeto p = service.salvar(projetoDTO);
      return p;
   }

   @GetMapping("{id}")
   public ProjetoDTO getProjetoPorId(@PathVariable Long id) {
      return service.obterProjetoPorId(id);
   }

   @GetMapping("/{idProjeto}/funcionarios")
   public DadosProjetoComFuncionariosDTO buscarProjetoComFuncionarios(@PathVariable Long idProjeto) {
      return service.buscarProjetoComFuncionarios(idProjeto);
   }

   @GetMapping
   public List<ProjetoDTO> getProjeto() {
      return service.obterTodos();
   }

   @PutMapping("{id}")
   public void editProjeto(@PathVariable Long id, @RequestBody ProjetoDTO dto) {
      service.editar(id, dto);
   }

   @DeleteMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteProjeto(@PathVariable Long id) {
      service.remover(id);
   }

   @PostMapping("/{idProjeto}/funcionarios/{idFuncionario}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void vincularFuncionario(
         @PathVariable Long idProjeto,
         @PathVariable Long idFuncionario) {
      service.vincularFuncionario(idProjeto, idFuncionario);
   }

   @GetMapping("/periodo")
   public List<DadosProjetoDTO> buscarPorPeriodo(
         @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
         @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
      return service.buscarProjetosPorPeriodo(inicio, fim);

   }

}
