package com.atividade1.ministerio.services;

import com.atividade1.ministerio.dao.MinistroDao;
import com.atividade1.ministerio.dao.PresidenteDao;
import com.atividade1.ministerio.dto.auth.AuthDto;
import com.atividade1.ministerio.dto.auth.UserResponseDto;
import com.atividade1.ministerio.enumerable.EUserType;
import com.atividade1.ministerio.models.Ministro;
import com.atividade1.ministerio.models.Presidente;

public class AuthService {
    private final MinistroDao _ministroDao;
    private final PresidenteDao _presidenteDao;

    public AuthService() {
        _ministroDao = MinistroDao.getInstance();
        _presidenteDao = PresidenteDao.getInstance();
    }

    public UserResponseDto Login(AuthDto request) throws Exception {
        Presidente presidente = _presidenteDao.GetByCpf(request.cpf);
        Ministro ministro = _ministroDao.GetByCpf(request.cpf);

        if (presidente != null && presidente.getPassword().equals(request.password)) {
            return new UserResponseDto(presidente.getId(), presidente.getPartido(), presidente.getDataEntrada(), presidente.getDataSaida(), presidente.getNome(), presidente.getCpf(), presidente.getSalario(), EUserType.Presidente);
        } else if (ministro != null && ministro.getPassword().equals(request.password)) {
            return new UserResponseDto(ministro.getId(), ministro.getPartido(), ministro.getDataEntrada(), ministro.getDataSaida(), ministro.getNome(), ministro.getCpf(), ministro.getSalario(), EUserType.Ministro);
        } else {
            throw new Exception("Usuário inválido");
        }
    }
}
