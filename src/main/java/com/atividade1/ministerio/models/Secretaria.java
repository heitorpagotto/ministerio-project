package com.atividade1.ministerio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Secretaria {
    public long Id;
    public String nome;
    public float orcamento;
    public int totalFuncionarios;
}
