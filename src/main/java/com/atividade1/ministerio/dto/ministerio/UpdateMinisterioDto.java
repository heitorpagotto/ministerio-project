package com.atividade1.ministerio.dto.ministerio;

import lombok.Data;

@Data
public class UpdateMinisterioDto {
    public int id;
    public float orcamento;
    public String nome;
    public int totalFuncionarios;
    public int idMinistro;
}
