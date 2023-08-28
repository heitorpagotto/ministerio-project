package com.atividade1.ministerio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Secretaria {
    public int id;
    public String nome;
    public float orcamento;
    public int totalFuncionarios;
    public int idMinisterio;
    public Ministerio ministerio;
}
