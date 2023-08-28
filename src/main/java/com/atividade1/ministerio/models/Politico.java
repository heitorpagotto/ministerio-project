package com.atividade1.ministerio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Politico {
    public int id;
    public String partido;
    public Date dataEntrada;
    public Date dataSaida;
    public String nome;
    public String cpf;
    public String password;
    public float salario;
}
