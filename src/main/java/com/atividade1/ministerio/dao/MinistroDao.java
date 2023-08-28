package com.atividade1.ministerio.dao;

import com.atividade1.ministerio.models.Ministerio;
import com.atividade1.ministerio.models.Ministro;
import com.atividade1.ministerio.models.Presidente;

import java.util.*;

public class MinistroDao {
    private Map<Integer, Ministro> ministros;
    private static volatile MinistroDao instance;

    public MinistroDao() {
        this.ministros = new TreeMap<>();
    }

    public static MinistroDao getInstance() {
        if (instance == null ) {
            instance = new MinistroDao();
        }

        return instance;
    }

    public Ministro GetByCpf(String cpf) {
        Optional<Ministro> minister = ministros.values().stream().filter(v->v.getCpf().equals(cpf)).findFirst();

        return minister.get();
    }
    public Ministro GetById(int id) {
        return ministros.get(id);
    }

    public List<Ministro> ListAll() {
        return new ArrayList<>(ministros.values());
    }

    public int Add(Ministro ministro) {
        ministros.put(ministro.getId(), ministro);
        return ministro.getId();
    }

    public int Update(Ministro ministro) {
        ministros.put(ministro.getId(), ministro);
        return ministro.getId();
    }

    public int Delete(int id) {
        ministros.remove(id);
        return id;
    }

    public int GetLastId() {
        return ministros.values().size();
    }
}
