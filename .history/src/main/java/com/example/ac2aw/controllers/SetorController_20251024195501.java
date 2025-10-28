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

import com.example.ac2aw.dtos.SetorCreateUpdateDTO;
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
   public SetorDTO salvar(@RequestBody SetorCreateUpdateDTO dto) {
      Setor s = new Setor(dto);
      service.save(s);
      return new SetorDTO(s);
   }

   @GetMapping("{id}")
   @ResponseStatus(HttpStatus.OK)
   public SetorDTO getSetorPorId(@PathVariable int id) {
      Setor s = service.get(id);
      return new SetorDTO(s);
   }

   @DeleteMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteSetor(@PathVariable int id) {
      service.remove(id);
   }

   @PutMapping("{id}")
   @ResponseStatus(HttpStatus.OK)
   public void editSetor(@PathVariable int id, @RequestBody SetorCreateUpdateDTO dto) {
      Setor s = new Setor(dto);
      service.editar(id, dto);
   }

   @GetMapping
   @ResponseStatus(HttpStatus.OK)
   public List<DadosSetorDTO> getSetor() {
      return service.obterTodos();
   }

   @GetMapping("/{idSetor}")
   @ResponseStatus(HttpStatus.OK)
   public DadosSetorDTO buscarSetorPorId(@PathVariable Integer idSetor) {
      return service.buscarSetorPorId(idSetor);
   }

   @GetMapping("/setor-com-funcionarios")
   @ResponseStatus(HttpStatus.OK)
   public List<DadosSetorComFuncionariosDTO> listarSetoresComFuncionarios() {
      return service.listarSetoresComFuncionarios();
   }

}
