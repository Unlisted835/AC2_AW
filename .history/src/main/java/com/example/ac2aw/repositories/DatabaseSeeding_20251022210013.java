package com.example.ac2aw.repositories;

import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.models.Projeto;
import com.example.ac2aw.models.Setor;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Bean
@RequiredArgsConstructor
public class DatabaseSeeding implements CommandLineRunner {

   private final SetorRepository setorRepository;
   private final FuncionarioRepository funcionarioRepository;
   private final ProjetoRepository projetoRepository;

   @Override
   @Transactional
   public void run() {
      if (setorRepository.count() > 0 || funcionarioRepository.count() > 0 || projetoRepository.count() > 0) {
         return;
      }

      List<Setor> setores = createSetores();
      setores = setorRepository.saveAll(setores);

      List<Projeto> projetos = createProjetos();
      projetos = projetoRepository.saveAll(projetos);

      List<Funcionario> funcionarios = createFuncionarios(setores, projetos);
      funcionarioRepository.saveAll(funcionarios);
   }

   private List<Setor> createSetores() {
      List<Setor> setores = new ArrayList<>();
      setores.add(new Setor("R.H."));
      setores.add(new Setor("Financeiro"));
      setores.add(new Setor("Desenvolvimento"));
      setores.add(new Setor("Marketing"));
      setores.add(new Setor("Vendas"));
      return setores;
   }

   private List<Projeto> createProjetos() {
      List<Projeto> projetos = new ArrayList<>();
      projetos.add(new Projeto("Projeto Alpha", LocalDate.now().minusMonths(6), LocalDate.now().plusMonths(6)));
      projetos.add(new Projeto("Projeto Beta", LocalDate.now().minusMonths(3), LocalDate.now().plusMonths(9)));
      projetos.add(new Projeto("Projeto Gama", LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(3)));
      projetos.add(new Projeto("Projeto Delta", LocalDate.now().plusDays(10), LocalDate.now().plusMonths(1)));
      projetos.add(new Projeto("Projeto Epsilon", LocalDate.now().minusWeeks(2), LocalDate.now().plusMonths(2)));
      return projetos;
   }

   private List<Funcionario> createFuncionarios(List<Setor> setores, List<Projeto> projetos) {
      List<Funcionario> funcionarios = new ArrayList<>();

      Funcionario f1 = new Funcionario("Ana Silva");
      f1.setSetor(setores.get(0));
      f1.setProjetos(List.of(projetos.get(0), projetos.get(3)));
      funcionarios.add(f1);

      Funcionario f2 = new Funcionario("Bruno Costa");
      f2.setSetor(setores.get(1));
      f2.setProjetos(List.of(projetos.get(1), projetos.get(4)));
      funcionarios.add(f2);

      Funcionario f3 = new Funcionario("Carlos Souza");
      f3.setSetor(setores.get(2));
      f3.setProjetos(List.of(projetos.get(0), projetos.get(1), projetos.get(2)));
      funcionarios.add(f3);

      Funcionario f4 = new Funcionario("Daniela Lima");
      f4.setSetor(setores.get(3));
      f4.setProjetos(List.of(projetos.get(2), projetos.get(3)));
      funcionarios.add(f4);

      Funcionario f5 = new Funcionario("Eduardo Mendes");
      f5.setSetor(setores.get(4));
      f5.setProjetos(List.of(projetos.get(4), projetos.get(0)));
      funcionarios.add(f5);
      
      projetoRepository.saveAll(projetos);

      return funcionarios;
   }
}