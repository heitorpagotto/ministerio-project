package com.atividade1.ministerio.dto.ministerio;

import lombok.Data;

@Data
public class AddMinisterioDto {
    public float orcamento;
    public String nome;
    public int totalFuncionarios;
    public int idMinistro;
}
