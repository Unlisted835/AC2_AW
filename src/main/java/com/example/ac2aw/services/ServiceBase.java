package com.example.ac2aw.services;

import java.util.List;

public interface ServiceBase<Entity, Id> {

   Entity save(Entity entity);

   Entity edit(Entity entity);

   void remove(Id id);

   Entity get(Id id);

   List<Entity> list();

}
