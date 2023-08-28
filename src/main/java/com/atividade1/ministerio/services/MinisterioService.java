package com.atividade1.ministerio.services;

import com.atividade1.ministerio.dao.MinisterioDao;
import com.atividade1.ministerio.dao.MinisterioDao;
import com.atividade1.ministerio.dao.MinistroDao;
import com.atividade1.ministerio.dto.ministerio.AddMinisterioDto;
import com.atividade1.ministerio.dto.ministerio.UpdateMinisterioDto;
import com.atividade1.ministerio.models.Ministerio;
import com.atividade1.ministerio.models.Ministro;

import java.util.List;

public class MinisterioService {
    private final MinisterioDao _dao;
    private final MinistroDao _ministroDao;

    public MinisterioService() {
        _dao = new MinisterioDao();
        _ministroDao = MinistroDao.getInstance();
    }

    public List<Ministerio> ListAll() {
        List<Ministerio> ministerios = _dao.ListAll();
        ministerios.forEach(x -> {
            x.getMinistro().setMinisterio(null);
        });

        return ministerios;
    }

    public Ministerio GetById(int id) throws Exception {
        Ministerio ministerio = _dao.GetById(id);

        if (ministerio == null)
            throw new Exception("Ministerio não encontrado");

        ministerio.getMinistro().setMinisterio(null);

        return ministerio;
    }

    public int Insert(AddMinisterioDto request) throws Exception {
        int newId = _dao.GetLastId() + 1;

        Ministerio ministerio = new Ministerio();

        ministerio.setId(newId);
        ministerio.setNome(request.getNome());

        Ministro ministro = _ministroDao.GetById(request.getIdMinistro());
        if (ministro == null)
            throw new Exception("Ministro não encontrado.");
        if (ministro.getMinisterio() != null)
            throw new Exception("Ministro já gerencia um ministério.");

        ministerio.setMinistro(ministro);
        ministerio.setOrcamento(request.getOrcamento());
        ministerio.setTotalFuncionarios(request.getTotalFuncionarios());

        _dao.Add(ministerio);

        ministro.setMinisterio(ministerio);
        _ministroDao.Update(ministro);

        return newId;
    }

    public int Update(UpdateMinisterioDto request) throws Exception {
        Ministerio ministerio = _dao.GetById(request.getId());

        if (ministerio == null)
            throw new Exception("Ministério não encontrado");

        if (request.getIdMinistro() != ministerio.getMinistro().getId()) {
            Ministro ministro = _ministroDao.GetById(request.getIdMinistro());
            if (ministro == null)
                throw new Exception("Ministro não encontrado");

            ministro.setMinisterio(null);
            ministerio.setMinistro(ministro);

            _ministroDao.Update(ministro);
        }
        ministerio.setNome(request.getNome());
        ministerio.setOrcamento(request.getOrcamento());
        ministerio.setTotalFuncionarios(request.getTotalFuncionarios());

        _dao.Update(ministerio);

        return request.getId();
    }

    public int Delete(int id) throws Exception {
        Ministerio ministerio = _dao.GetById(id);

        if (ministerio == null)
            throw new Exception("Ministério não encontrado");

        _dao.Delete(id);

        return id;
    }
}
