package com.atividade1.ministerio.dao;

import com.atividade1.ministerio.models.Secretaria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SecretariaDao {
    private Map<Integer, Secretaria> secretarias;

    public SecretariaDao() {
        this.secretarias = new TreeMap<>();
    }

    public Secretaria GetById(int id) {
        return secretarias.get(id);
    }

    public List<Secretaria> ListAll() {
        return new ArrayList<>(secretarias.values());
    }

    public int Add(Secretaria secretaria) {
        secretarias.put(secretaria.getId(), secretaria);
        return secretaria.getId();
    }

    public int Update(Secretaria secretaria) {
        secretarias.put(secretaria.getId(), secretaria);
        return secretaria.getId();
    }

    public int Delete(int id) {
        secretarias.remove(id);
        return id;
    }

    public int GetLastId() {
        return secretarias.values().size();
    }
}
