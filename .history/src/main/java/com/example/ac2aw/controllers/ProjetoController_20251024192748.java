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

@RestController
@RequestMapping("/projeto")
@CrossOrigin(allowedHeaders = "*")
public class ProjetoController {

   private ProjetoService projetoService;

   public ProjetoController(ProjetoService projetoService) {
      this.projetoService = projetoService;
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public Projeto salvar(@RequestBody ProjetoDTO projetoDTO) {
      Projeto p = projetoService.salvar(projetoDTO);
      return p;
   }

   @GetMapping("{id}")
   public ProjetoDTO getProjetoPorId(@PathVariable Long id) {
      return projetoService.obterProjetoPorId(id);
   }

   @GetMapping("/{idProjeto}/funcionarios")
   public DadosProjetoComFuncionariosDTO buscarProjetoComFuncionarios(@PathVariable Long idProjeto) {
      return projetoService.buscarProjetoComFuncionarios(idProjeto);
   }

   @GetMapping
   public List<ProjetoDTO> getProjeto() {
      return projetoService.obterTodos();
   }

   @PutMapping("{id}")
   public void editProjeto(@PathVariable Long id, @RequestBody ProjetoDTO dto) {
      projetoService.editar(id, dto);
   }

   @DeleteMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteProjeto(@PathVariable Long id) {
      projetoService.remover(id);
   }

   @PostMapping("/{idProjeto}/funcionarios/{idFuncionario}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void vincularFuncionario(
         @PathVariable Long idProjeto,
         @PathVariable Long idFuncionario) {
      projetoService.vincularFuncionario(idProjeto, idFuncionario);
   }

   @GetMapping("/periodo")
   public List<DadosProjetoDTO> buscarPorPeriodo(
         @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
         @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
      return projetoService.buscarProjetosPorPeriodo(inicio, fim);

   }

}
