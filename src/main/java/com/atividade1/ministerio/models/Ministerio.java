package com.atividade1.ministerio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ministerio {
    private int id;
    private String nome;
    private float orcamento;
    private int totalFuncionarios;
    private ArrayList<Secretaria> secretarias;
    private Ministro ministro;
}
