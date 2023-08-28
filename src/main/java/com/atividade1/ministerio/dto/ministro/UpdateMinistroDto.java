package com.atividade1.ministerio.dto.ministro;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateMinistroDto {
    public int id;
    public String nome;
    public String partido;
    public float salario;
    public Date dataSaida;
    public String cpf;
    public String password;
}
