package com.example.ac2aw.services;

import java.util.List;

import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.models.Projeto;
import com.example.ac2aw.models.Setor;

public interface FuncionarioService extends ServiceBase<Funcionario, Integer> {

   List<Projeto> listAllRelatedProjects(int funcionarioId);

   Setor findSetorById(int id);

}
