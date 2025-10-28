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

import com.example.ac2aw.dtos.SetorDTO;
import com.example.ac2aw.models.Setor;
import com.example.ac2aw.services.SetorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/setor")
@CrossOrigin(allowedHeaders = "*")
@RequiredArgsConstructor
public class SetorController {

   private final SetorService service;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public SetorDTO salvar(@RequestBody SetorDTO dto) {
      Setor s = new Setor(dto);
      return c;
   }

   @GetMapping("{id}")
   public DadosSetorDTO getSetorPorId(@PathVariable Long id) {
      return service.obterSetorPorId(id);
   }

   @DeleteMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteSetor(@PathVariable Long id) {
      service.remover(id);
   }

   @PutMapping("{id}")
   public void editSetor(@PathVariable Long id, @RequestBody SetorDTO dto) {
      service.editar(id, dto);
   }

   @GetMapping
   public List<DadosSetorDTO> getSetor() {
      return service.obterTodos();
   }

   @GetMapping("/{idSetor}")
   public DadosSetorDTO buscarSetorPorId(@PathVariable Integer idSetor) {
      return service.buscarSetorPorId(idSetor);
   }

   @GetMapping("/setor-com-funcionarios")
   public List<DadosSetorComFuncionariosDTO> listarSetoresComFuncionarios() {
      return service.listarSetoresComFuncionarios();
   }

}
