package com.atividade1.ministerio.dto.secretaria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateSecretariaDto {
    public int id;
    public String nome;
    public float orcamento;
    public int totalFuncionarios;
}
