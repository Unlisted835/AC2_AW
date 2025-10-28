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
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSeeding {

   private final SetorRepository setorRepository;
   private final FuncionarioRepository funcionarioRepository;
   private final ProjetoRepository projetoRepository;
   
   @Transactional
   public void run() throws Exception {
      if (setorRepository.count() > 0 || funcionarioRepository.count() > 0 || projetoRepository.count() > 0) {
         System.out.println("Database already contains data. Skipping seeding.");
         return;
      }

      System.out.println("Starting database seeding...");

      // 1. Create and Save 5 Setores
      List<Setor> setores = createSetores();
      setores = setorRepository.saveAll(setores);
      System.out.println("Seeded " + setores.size() + " Setores.");

      // 2. Create and Save 5 Projetos
      List<Projeto> projetos = createProjetos();
      projetos = projetoRepository.saveAll(projetos);
      System.out.println("Seeded " + projetos.size() + " Projetos.");

      // 3. Create and Save 5 Funcionarios and establish relationships
      List<Funcionario> funcionarios = createFuncionarios(setores, projetos);
      funcionarioRepository.saveAll(funcionarios); // Relationships are managed on the owning side (Funcionario for
                                                   // Setor)
      System.out.println("Seeded " + funcionarios.size() + " Funcionarios and established relationships.");

      // Important: Update the ManyToMany relationship (Projeto -> Funcionario)
      // Since Funcionario is the owning side in the ManyToMany (Funcionario has
      // mappedBy="funcionarios" and Projeto has mappedBy="projetos"),
      // the relationship is defined by the Funcionario entity's persistence,
      // but the 'projetos' list in Projeto needs to be updated if you want to access
      // it from the Projeto side.
      // However, looking at your mappings:
      // Funcionario has: @ManyToMany(mappedBy = "funcionarios") @JoinColumn(name =
      // "projeto_id") private Projeto projeto; <-- This seems incorrect for
      // ManyToMany, should be List<Projeto>
      // Projeto has: @ManyToMany (mappedBy = "projetos") @JoinColumn(name =
      // "funcionario_id") private List<Funcionario> funcionarios; <-- This is the
      // owning side in this config!

      // **Correction based on standard JPA practice:**
      // The entity without `mappedBy` (or with `@JoinTable`) is usually the **owning
      // side**.
      // In your provided code, the relationship mapping is unconventional and likely
      // incorrect:
      // - Funcionario: Has `mappedBy = "funcionarios"` but also `@JoinColumn(name =
      // "projeto_id")` and the type is singular `Projeto`. This looks like a mix-up
      // between ManyToOne and ManyToMany.
      // - Projeto: Has `mappedBy = "projetos"` and the type is `List<Funcionario>`.
      // This would typically mean Projeto is the **inverse side**.

      // Assuming the intent was that **Funcionario is the Owning Side** of the
      // Many-to-Many relationship (which is good practice for the *many* side):
      // 1. **Funcionario** should define the `@ManyToMany` with `@JoinTable` (or
      // `@JoinColumn` on the intermediate table if Hibernate DDL is used)
      // *AND* its attribute should be `List<Projeto>`.
      // 2. **Projeto** should define the `@ManyToMany(mappedBy = "projetos")` and its
      // attribute should be `List<Funcionario>`.

      // Since I must use your entities as is, I will **assume** the application is
      // configured such that the saving of `Funcionario` *correctly* establishes the
      // link in the join table, even with the unconventional singular `Projeto
      // projeto;` and `mappedBy` on the `Funcionario` side.
      // The following assumes the relationships are correctly set by saving the
      // Funcionario objects *with* their associated Setor and Projeto/s.

      System.out.println("Database seeding completed successfully! ✨");
   }

   // --- Data Creation Helper Methods ---

   private List<Setor> createSetores() {
      List<Setor> setores = new ArrayList<>();
      setores.add(new Setor("HR"));
      setores.add(new Setor("Finance"));
      setores.add(new Setor("IT"));
      setores.add(new Setor("Marketing"));
      setores.add(new Setor("Sales"));
      return setores;
   }

   private List<Projeto> createProjetos() {
      List<Projeto> projetos = new ArrayList<>();
      projetos.add(new Projeto("Cloud Migration", LocalDate.now().minusMonths(6), LocalDate.now().plusMonths(6)));
      projetos
            .add(new Projeto("New CRM Implementation", LocalDate.now().minusMonths(3), LocalDate.now().plusMonths(9)));
      projetos.add(new Projeto("Website Redesign", LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(3)));
      projetos.add(new Projeto("Q4 Budget Planning", LocalDate.now().plusDays(10), LocalDate.now().plusMonths(1)));
      projetos.add(new Projeto("Recruitment Drive", LocalDate.now().minusWeeks(2), LocalDate.now().plusMonths(2)));
      return projetos;
   }

   private List<Funcionario> createFuncionarios(List<Setor> setores, List<Projeto> projetos) {
      List<Funcionario> funcionarios = new ArrayList<>();

      // Create 5 employees, assigning them to a sector and a project

      // Employee 1: HR, Cloud Migration
      Funcionario f1 = new Funcionario("Alice Johnson");
      f1.setSetor(setores.get(0)); // HR
      f1.setProjeto(projetos.get(0)); // Cloud Migration
      funcionarios.add(f1);

      // Employee 2: Finance, New CRM Implementation
      Funcionario f2 = new Funcionario("Bob Smith");
      f2.setSetor(setores.get(1)); // Finance
      f2.setProjeto(projetos.get(1)); // New CRM Implementation
      funcionarios.add(f2);

      // Employee 3: IT, Website Redesign
      Funcionario f3 = new Funcionario("Charlie Brown");
      f3.setSetor(setores.get(2)); // IT
      f3.setProjeto(projetos.get(2)); // Website Redesign
      funcionarios.add(f3);

      // Employee 4: Marketing, Q4 Budget Planning
      Funcionario f4 = new Funcionario("Dana White");
      f4.setSetor(setores.get(3)); // Marketing
      f4.setProjeto(projetos.get(3)); // Q4 Budget Planning
      funcionarios.add(f4);

      // Employee 5: Sales, Recruitment Drive
      Funcionario f5 = new Funcionario("Eve Black");
      f5.setSetor(setores.get(4)); // Sales
      f5.setProjeto(projetos.get(4)); // Recruitment Drive
      funcionarios.add(f5);

      // Due to the unconventional mapping (Funcionario having `Projeto projeto`),
      // this only establishes one link per employee.
      // If a true Many-to-Many was intended, Funcionario should have `List<Projeto>
      // projetos`,
      // and you would add multiple projects here.

      return funcionarios;
   }
}