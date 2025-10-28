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

@RestController
@RequestMapping("/setor")
@CrossOrigin(allowedHeaders = "*")
public class SetorController {

   private SetorService SetorService;

   public SetorController(SetorService setorService) {
      this.SetorService = setorService;
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public Setor salvar(@RequestBody SetorDTO setorDTO) {
      Setor c = setorService.salvar(setorDTO);
      return c;
   }

   @GetMapping("{id}")
   public DadosSetorDTO getSetorPorId(@PathVariable Long id) {
      return setorService.obterSetorPorId(id);
   }

   @DeleteMapping("{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteSetor(@PathVariable Long id) {
      setorService.remover(id);
   }

   @PutMapping("{id}")
   public void editSetor(@PathVariable Long id, @RequestBody SetorDTO dto) {
      setorService.editar(id, dto);
   }

   @GetMapping
   public List<DadosSetorDTO> getSetor() {
      return setorService.obterTodos();
   }

   @GetMapping("/{idSetor}")
   public DadosSetorDTO buscarSetorPorId(@PathVariable Integer idSetor) {
      return setorService.buscarSetorPorId(idSetor);
   }

   @GetMapping("/setor-com-funcionarios")
   public List<DadosSetorComFuncionariosDTO> listarSetoresComFuncionarios() {
      return setorService.listarSetoresComFuncionarios();
   }

}
