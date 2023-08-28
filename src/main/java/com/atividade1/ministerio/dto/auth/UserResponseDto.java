package com.atividade1.ministerio.dto.auth;

import com.atividade1.ministerio.enumerable.EUserType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserResponseDto {
    public int id;
    public String partido;
    public Date dataEntrada;
    public Date dataSaida;
    public String nome;
    public String cpf;
    public float salario;
    public EUserType userType;
}
