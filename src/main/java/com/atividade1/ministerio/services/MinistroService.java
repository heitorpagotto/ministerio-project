package com.atividade1.ministerio.services;

import com.atividade1.ministerio.dao.MinisterioDao;
import com.atividade1.ministerio.dao.MinistroDao;
import com.atividade1.ministerio.dto.ministro.AddMinistroDto;
import com.atividade1.ministerio.dto.ministro.UpdateMinistroDto;
import com.atividade1.ministerio.models.Ministro;

import java.util.List;

public class MinistroService {
    private final MinistroDao _dao;
    private final MinisterioDao _ministerioDao;

    public MinistroService() {
        _dao = MinistroDao.getInstance();
        _ministerioDao = MinisterioDao.getInstance();
    }

    public List<Ministro> ListAll() {
        List<Ministro> ministros = _dao.ListAll();

        ministros.forEach(m -> {
            m.setPassword(null);
            m.setMinisterio(_ministerioDao.GetById(m.getIdMinisterio()));
            if (m.getMinisterio() != null)
                m.getMinisterio().setMinistro(null);
        });

        return ministros;
    }

    public Ministro GetById(int id) throws Exception {
        Ministro ministro = _dao.GetById(id);

        if (ministro == null)
            throw new Exception("Ministro não encontrado");

        ministro.setPassword(null);
        ministro.setMinisterio(_ministerioDao.GetById(ministro.getIdMinisterio()));
        if (ministro.getMinisterio() != null)
            ministro.getMinisterio().setMinistro(null);

        return ministro;
    }

    public int Delete(int id) throws Exception {
        Ministro ministro = _dao.GetById(id);

        if (ministro == null)
            throw new Exception("Ministro não encontrado");

        _ministerioDao.Delete(ministro.getIdMinisterio());

        _dao.Delete(id);

        return id;
    }

    public int Update(UpdateMinistroDto request) throws Exception {
        Ministro ministro = _dao.GetById(request.getId());

        if (ministro == null)
            throw new Exception("Ministro não encontrado");

        if (request.getDataSaida().compareTo(ministro.getDataEntrada()) < 0)
            throw new Exception("Data de saída não pode ser menor que Data de entrada.");

        if (!ministro.getCpf().equals(request.getCpf())) {
            var existingMinistro = _dao.GetByCpf(request.getCpf());
            if (existingMinistro != null)
                throw new Exception("Ministro já cadastrado.");
        }

        ministro.setDataSaida(request.getDataSaida());
        ministro.setNome(request.getNome());
        ministro.setSalario(request.getSalario());
        ministro.setPartido(request.getPartido());
        ministro.setCpf(request.getCpf());
        ministro.setPassword(request.getPassword());

        return _dao.Update(ministro);
    }

    public int Insert(AddMinistroDto request) throws Exception {
        int newId = _dao.GetLastId() + 1;

        Ministro ministroToInsert = new Ministro();

        ministroToInsert.setId(newId);
        ministroToInsert.setNome(request.getNome());
        ministroToInsert.setCpf(request.getCpf());
        ministroToInsert.setPassword(request.getPassword());
        ministroToInsert.setPartido(request.getPartido());
        ministroToInsert.setSalario(request.getSalario());
        ministroToInsert.setDataEntrada(request.getDataEntrada());
        ministroToInsert.setDataSaida(request.getDataSaida());

        var existingMinistro = _dao.GetByCpf(request.getCpf());
        if (existingMinistro != null)
            throw new Exception("Ministro já cadastrado.");
        if (request.getDataSaida().compareTo(request.getDataEntrada()) < 0)
            throw new Exception("Data de saída não pode ser menor que Data de entrada.");

        _dao.Add(ministroToInsert);

        return newId;
    }
}
