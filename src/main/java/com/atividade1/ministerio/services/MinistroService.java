package com.atividade1.ministerio.services;

import com.atividade1.ministerio.dao.MinistroDao;
import com.atividade1.ministerio.dao.MinistroDao;
import com.atividade1.ministerio.dto.ministro.AddMinistroDto;
import com.atividade1.ministerio.dto.ministro.UpdateMinistroDto;
import com.atividade1.ministerio.models.Ministro;

import java.util.List;

public class MinistroService {
    private final MinistroDao _dao;

    public MinistroService() {
        _dao = MinistroDao.getInstance();
    }

    public List<Ministro> ListAll() {
        return _dao.ListAll();
    }

    public Ministro GetById(int id) throws Exception {
        Ministro ministro = _dao.GetById(id);

        if (ministro == null)
            throw new Exception("Ministro não encontrado");

        return ministro;
    }

    public int Delete(int id) throws Exception {
        Ministro ministro = _dao.GetById(id);

        if (ministro == null)
            throw new Exception("Ministro não encontrado");

        _dao.Delete(id);

        return id;
    }

    public int Update(UpdateMinistroDto request) throws Exception {
        Ministro ministro = _dao.GetById(request.getId());

        if (ministro == null)
            throw new Exception("Ministro não encontrado");

        if (request.getDataSaida().compareTo(ministro.getDataEntrada()) < 0)
            throw new Exception("Data de saída não pode ser menor que Data de entrada.");

        ministro.setDataSaida(request.getDataSaida());
        ministro.setNome(request.getNome());
        ministro.setSalario(request.getSalario());
        ministro.setPartido(request.getPartido());



        return _dao.Update(ministro);
    }

    public int Insert(AddMinistroDto request) throws Exception {
        int newId = _dao.GetLastId() + 1;

        Ministro ministroToInsert = new Ministro();

        ministroToInsert.setId(newId);
        ministroToInsert.setNome(request.getNome());
        ministroToInsert.setPartido(request.getPartido());
        ministroToInsert.setSalario(request.getSalario());
        ministroToInsert.setDataEntrada(request.getDataEntrada());
        ministroToInsert.setDataSaida(request.getDataSaida());

        if (request.getDataSaida().compareTo(request.getDataEntrada()) < 0)
            throw new Exception("Data de saída não pode ser menor que Data de entrada.");

        _dao.Add(ministroToInsert);

        return newId;
    }
}
