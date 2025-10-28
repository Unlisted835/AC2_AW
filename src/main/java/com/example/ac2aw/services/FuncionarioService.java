package com.example.ac2aw.services;

import java.util.List;

import com.example.ac2aw.models.Funcionario;
import com.example.ac2aw.models.Projeto;

public interface FuncionarioService extends ServiceBase<Funcionario, Integer> {

   List<Projeto> listAllRelatedProjects(int funcionarioId);

}
