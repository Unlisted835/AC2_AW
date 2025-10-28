package com.example.ac2aw.dtos;

import java.time.LocalDate;

public record ProjetoCreateUpdateDTO(String descricao, LocalDate dataInicio, LocalDate dataFim) {

}
