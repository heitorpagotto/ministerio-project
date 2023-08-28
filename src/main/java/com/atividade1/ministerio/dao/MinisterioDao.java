package com.atividade1.ministerio.dao;

import com.atividade1.ministerio.models.Ministerio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MinisterioDao {
    private Map<Integer, Ministerio> ministerios;

    public MinisterioDao() {
        this.ministerios = new TreeMap<>();
    }

    public Ministerio GetById(int id) {
        return ministerios.get(id);
    }

    public List<Ministerio> ListAll() {
        return new ArrayList<>(ministerios.values());
    }

    public int Add(Ministerio ministerio) {
        ministerios.put(ministerio.getId(), ministerio);
        return ministerio.getId();
    }

    public int Update(Ministerio ministerio) {
        ministerios.put(ministerio.getId(), ministerio);
        return ministerio.getId();
    }

    public int Delete(int id) {
        ministerios.remove(id);
        return id;
    }

    public int GetLastId() {
        return ministerios.values().size();
    }
}
