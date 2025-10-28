package com.example.ac2aw.services;

import java.util.List;

public interface ServiceBase<TEntity> {

   public TEntity salvar(TEntity entity);

   public TEntity atualizar(TEntity entity);

   public void deletar(Long id);

   public TEntity buscarPorId(Long id);

   public List<TEntity> buscarTodos();

}
