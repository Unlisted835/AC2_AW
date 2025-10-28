package com.example.ac2aw.services;

import java.util.List;

import com.example.ac2aw.models.Setor;

public interface SetorService extends ServiceBase<Setor, Integer> {

   public List<Setor> listAllIncludingFuncionarios();

}
