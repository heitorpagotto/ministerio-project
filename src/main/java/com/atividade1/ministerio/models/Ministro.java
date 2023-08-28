package com.atividade1.ministerio.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Ministro extends Politico {
    public int idMinisterio;
    public Ministerio ministerio;
}
