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

@Component
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
      f1.setSetor(setores.get(0)); // R.H.
      f1.setProjetos(List.of(projetos.get(0), projetos.get(3))); // Alpha, Delta
      funcionarios.add(f1);

      Funcionario f2 = new Funcionario("Bruno Costa");
      f2.setSetor(setores.get(1)); // Financeiro
      f2.setProjetos(List.of(projetos.get(1), projetos.get(4))); // Beta, Epsilon
      funcionarios.add(f2);

      Funcionario f3 = new Funcionario("Carlos Souza");
      f3.setSetor(setores.get(2)); // Desenvolvimento
      f3.setProjetos(List.of(projetos.get(0), projetos.get(1), projetos.get(2))); // Alpha, Beta, Gama
      funcionarios.add(f3);

      Funcionario f4 = new Funcionario("Daniela Lima");
      f4.setSetor(setores.get(3)); // Marketing
      f4.setProjetos(List.of(projetos.get(2), projetos.get(3))); // Gama, Delta
      funcionarios.add(f4);

      Funcionario f5 = new Funcionario("Eduardo Mendes");
      f5.setSetor(setores.get(4)); // Vendas
      f5.setProjetos(List.of(projetos.get(4), projetos.get(0))); // Epsilon, Alpha
      funcionarios.add(f5);

      Funcionario f6 = new Funcionario("Fernanda Gomes");
      f6.setSetor(setores.get(0)); // R.H.
      f6.setProjetos(List.of(projetos.get(1), projetos.get(4))); // Beta, Epsilon
      funcionarios.add(f6);

      Funcionario f7 = new Funcionario("Gustavo Rocha");
      f7.setSetor(setores.get(2)); // Desenvolvimento
      f7.setProjetos(List.of(projetos.get(0), projetos.get(2))); // Alpha, Gama
      funcionarios.add(f7);

      Funcionario f8 = new Funcionario("Helena Neves");
      f8.setSetor(setores.get(1)); // Financeiro
      f8.setProjetos(List.of(projetos.get(3))); // Delta
      funcionarios.add(f8);

      Funcionario f9 = new Funcionario("Igor Santos");
      f9.setSetor(setores.get(4)); // Vendas
      f9.setProjetos(List.of(projetos.get(0), projetos.get(1), projetos.get(3))); // Alpha, Beta, Delta
      funcionarios.add(f9);

      Funcionario f10 = new Funcionario("Juliana Pires");
      f10.setSetor(setores.get(3)); // Marketing
      f10.setProjetos(List.of(projetos.get(2), projetos.get(4))); // Gama, Epsilon
      funcionarios.add(f10);

      projetoRepository.saveAll(projetos);

      return funcionarios;
   }
}