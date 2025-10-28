package com.example.ac2aw.services;

import java.util.List;

import com.example.ac2aw.dtos.FuncionarioCreateDTO;

public interface ServiceBase<Entity, Id> {

   public Entity save(FuncionarioCreateDTO dto);

   public Entity edit(Entity entity);

   public void remove(Id id);

   public Entity get(Id id);

   public List<Entity> list();

}
