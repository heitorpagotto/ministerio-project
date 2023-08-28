package com.atividade1.ministerio.services;

import com.atividade1.ministerio.dao.MinisterioDao;
import com.atividade1.ministerio.dao.MinisterioDao;
import com.atividade1.ministerio.dao.MinistroDao;
import com.atividade1.ministerio.dto.ministerio.AddMinisterioDto;
import com.atividade1.ministerio.models.Ministerio;
import com.atividade1.ministerio.models.Ministro;

public class MinisterioService {
    private final MinisterioDao _dao;
    private final MinistroDao _ministroDao;

    public MinisterioService() {
        _dao = new MinisterioDao();
        _ministroDao = MinistroDao.getInstance();
    }

    public int Insert(AddMinisterioDto ministerioDto) throws Exception {
        int newId = _dao.GetLastId() + 1;

        Ministerio ministerio = new Ministerio();

        ministerio.setId(newId);
        ministerio.setNome(ministerioDto.getNome());

        Ministro ministro = _ministroDao.GetById(ministerioDto.getIdMinistro());
        if (ministro == null)
            throw new Exception("Ministro não encontrado");

        ministerio.setMinistro(ministro);
        ministerio.setOrcamento(ministerioDto.getOrcamento());
        ministerio.setTotalFuncionarios(ministerioDto.getTotalFuncionarios());

        return newId;
    }
}
