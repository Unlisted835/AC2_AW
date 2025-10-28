package com.example.ac2aw.services;

import java.util.List;

public interface ServiceBase<Entity, Id> {

   public Entity save(Entity entity);

   public Entity atualizar(Entity entity);

   public void deletar(Id id);

   public Entity buscarPorId(Id id);

   public List<Entity> buscarTodos();

}
