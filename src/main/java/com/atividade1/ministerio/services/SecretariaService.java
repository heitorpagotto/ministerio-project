package com.atividade1.ministerio.services;

import com.atividade1.ministerio.dao.MinisterioDao;
import com.atividade1.ministerio.dao.SecretariaDao;
import com.atividade1.ministerio.dto.secretaria.AddSecretariaDto;
import com.atividade1.ministerio.dto.secretaria.UpdateSecretariaDto;
import com.atividade1.ministerio.models.Ministerio;
import com.atividade1.ministerio.models.Secretaria;

import java.util.ArrayList;
import java.util.List;

public class SecretariaService {
    private final SecretariaDao _dao;
    private final MinisterioDao _ministerioDao;

    public SecretariaService() {
        _dao = new SecretariaDao();
        _ministerioDao = MinisterioDao.getInstance();
    }

    public List<Secretaria> ListAll() {
        return _dao.ListAll();
    }

    public List<Secretaria> ListByMinisterioId(int id) throws Exception {
        Ministerio ministerio = _ministerioDao.GetById(id);
        if (ministerio == null)
            throw new Exception("Ministério não econtrado");
        if (ministerio.getSecretarias() == null)
            throw new Exception("Ministério não possuí secretarias.");

        return ministerio.getSecretarias().stream().toList();
    }

    public Secretaria GetById(int id) throws Exception {
        Secretaria secretaria = _dao.GetById(id);

        if (secretaria == null)
            throw new Exception("Secretaria não encontrada");

        Ministerio ministerio = _ministerioDao.GetById(secretaria.getIdMinisterio());

        ministerio.setSecretarias(null);

        secretaria.setMinisterio(ministerio);

        return secretaria;
    }

    public int Insert(AddSecretariaDto request) throws Exception {
        Ministerio ministerio = _ministerioDao.GetById(request.getIdMinisterio());
        var newId = _dao.GetLastId() + 1;

        if (ministerio == null)
            throw new Exception("Ministério não encontrado");

        Secretaria secretaria = new Secretaria();

        secretaria.setId(newId);
        secretaria.setNome(request.getNome());
        secretaria.setOrcamento(request.getOrcamento());
        secretaria.setTotalFuncionarios(request.getTotalFuncionarios());
        secretaria.setIdMinisterio(request.getIdMinisterio());

        ArrayList<Secretaria> ministerioSecretarias = ministerio.getSecretarias();

        if (ministerioSecretarias == null)
            ministerioSecretarias = new ArrayList<>();

        float orcamentoSum = ministerioSecretarias.stream()
                .map(Secretaria::getOrcamento)
                .reduce(0f, Float::sum);

        if (orcamentoSum + secretaria.getOrcamento() > ministerio.getOrcamento())
            throw new Exception("Máximo orçamento já atingido");

        ministerioSecretarias.add(secretaria);

        ministerio.setSecretarias(ministerioSecretarias);

        _ministerioDao.Update(ministerio);
        _dao.Add(secretaria);

        return newId;
    }

    public int Update(UpdateSecretariaDto request) throws Exception {
        Secretaria secretaria = _dao.GetById(request.getId());

        if (secretaria == null)
            throw new Exception("Secretaria não encontrada");

        Ministerio ministerio = _ministerioDao.GetById(secretaria.getIdMinisterio());
        if (ministerio == null)
            throw new Exception("Ministério não encontrado");

        secretaria.setNome(request.getNome());
        secretaria.setTotalFuncionarios(request.getTotalFuncionarios());

        ArrayList<Secretaria> ministerioSecretarias = ministerio.getSecretarias();

        if (ministerioSecretarias == null)
            ministerioSecretarias = new ArrayList<>();

        float orcamentoSum = ministerioSecretarias.stream()
                .map(s -> s.getOrcamento() - secretaria.getOrcamento())
                .reduce(0f, Float::sum);

        if (orcamentoSum + request.getOrcamento() > ministerio.getOrcamento())
            throw new Exception("Máximo orçamento já atingido");

        secretaria.setOrcamento(request.getOrcamento());

        ministerioSecretarias.remove(secretaria);
        ministerioSecretarias.add(secretaria);

        ministerio.setSecretarias(ministerioSecretarias);

        _ministerioDao.Update(ministerio);
        _dao.Update(secretaria);

        return secretaria.getId();
    }

    public int Delete(int id) throws Exception {
        Secretaria secretaria = _dao.GetById(id);
        if (secretaria == null)
            throw new Exception("Secretaria não encontrada");

        Ministerio ministerio = _ministerioDao.GetById(secretaria.getIdMinisterio());

        if (ministerio == null)
            throw new Exception("Ministério não encontrado");

        ArrayList<Secretaria> ministerioSecretarias = ministerio.getSecretarias();

        if (ministerioSecretarias != null) {
            ministerioSecretarias.remove(secretaria);
        } else {
            ministerioSecretarias = new ArrayList<>();
        }

        ministerio.setSecretarias(ministerioSecretarias);

        _ministerioDao.Update(ministerio);
        _dao.Delete(id);

        return id;
    }
}
