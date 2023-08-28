package com.atividade1.ministerio.dto.ministro;

import lombok.Data;

import java.util.Date;

@Data
public class AddMinistroDto {
    public String nome;
    public String partido;
    public Date dataEntrada;
    public Date dataSaida;
    public float salario;
    public String cpf;
    public String password;
}
