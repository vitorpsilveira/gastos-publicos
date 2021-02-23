package br.com.publico.gastos.services.impl;

import br.com.publico.gastos.controller.request.ColaboradorRequest;
import br.com.publico.gastos.domain.dto.mapper.ColaboradorMapper;
import br.com.publico.gastos.repository.ColaboradorRepository;
import br.com.publico.gastos.services.ColaboradorService;
import br.com.publico.gastos.services.exception.NomeJaExisteException;
import br.com.publico.gastos.services.exception.SiglaJaExisteException;
import br.com.publico.gastos.services.message.ValidationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColaboradorServiceImpl implements ColaboradorService {

    @Autowired
    private ColaboradorRepository repository;

    @Autowired
    private ColaboradorMapper colaboradorMapper;

    @Override
    public void salvar(ColaboradorRequest request) {
        var colaborador = colaboradorMapper.colaboradorRequestToEntity(request);
        var nomeJaExiste = repository.findByNome(colaborador.getNome());
        var siglaJaExiste = repository.findBySigla(colaborador.getSigla());

        if (nomeJaExiste.isPresent()) {
            throw new NomeJaExisteException(String.format(ValidationMessage.O_NOME_JA_EXISTE, nomeJaExiste.get().getNome()));
        }

        if (siglaJaExiste.isPresent()) {
            throw new SiglaJaExisteException(String.format(ValidationMessage.A_SIGLA_JA_EXISTE, siglaJaExiste.get().getSigla()));
        }

        repository.save(colaborador);
    }
}
