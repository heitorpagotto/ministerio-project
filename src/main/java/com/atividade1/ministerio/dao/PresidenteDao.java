package com.atividade1.ministerio.dao;

import com.atividade1.ministerio.models.Presidente;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class PresidenteDao {
    private static volatile PresidenteDao instance;
    private final Map<Integer, Presidente> presidente;

    public PresidenteDao() {
        this.presidente = new TreeMap<>();

        Presidente president = new Presidente();

        president.setId(1);
        president.setNome("Presidente República");
        president.setCpf("123456789");
        president.setPartido("PARTIDO");
        president.setVerbaPresidencial(200000000);
        president.setPassword("123456");
        president.setSalario(30000);
        president.setDataEntrada(new Date("2023-01-01"));
        president.setDataSaida(new Date("2027-01-01"));

        this.presidente.put(president.getId(), president);
    }

    public static PresidenteDao getInstance() {
        if (instance == null) {
            instance = new PresidenteDao();
        }

        return instance;
    }

    public Presidente GetByCpf(String cpf) {
        Optional<Presidente> president = presidente.values().stream().filter(v -> v.getCpf().equals(cpf)).findFirst();

        return president.get();
    }

    public Presidente GetById(int id) {
        return presidente.get(id);
    }

    public Presidente GetCurrentPresident(Date currentDate) {
        Optional<Presidente> president = presidente.values().stream().filter(v -> v.getDataEntrada().compareTo(currentDate) <= 0 && v.getDataSaida().compareTo(currentDate) > 0).findFirst();

        return president.get();
    }

    public int Update(Presidente presidente) {
        this.presidente.put(presidente.getId(), presidente);

        return presidente.getId();
    }
}
