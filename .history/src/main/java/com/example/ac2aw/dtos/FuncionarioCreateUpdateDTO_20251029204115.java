package com.example.ac2aw.dtos;

import com.example.ac2aw.models.Setor;

public record FuncionarioCreateUpdateDTO (String nome, int setorId, Setor setor) {

}
