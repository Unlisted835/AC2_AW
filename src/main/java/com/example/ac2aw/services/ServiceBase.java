package com.example.ac2aw.services;

import java.util.List;

public interface ServiceBase<Entity, Id> {

   public Entity save(Entity entity);

   public Entity edit(Entity entity);

   public void remove(Id id);

   public Entity get(Id id);

   public List<Entity> list();

}
