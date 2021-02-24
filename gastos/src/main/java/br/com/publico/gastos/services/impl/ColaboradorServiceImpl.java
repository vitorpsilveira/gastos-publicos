package br.com.publico.gastos.services.impl;

import br.com.publico.gastos.controller.request.ColaboradorRequest;
import br.com.publico.gastos.domain.dto.mapper.ColaboradorMapper;
import br.com.publico.gastos.repository.ColaboradorRepository;
import br.com.publico.gastos.services.ColaboradorService;
import br.com.publico.gastos.services.exception.EntidadeNaoEncontradaException;
import br.com.publico.gastos.services.exception.NomeJaExisteException;
import br.com.publico.gastos.services.exception.SiglaJaExisteException;
import br.com.publico.gastos.services.message.ValidationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

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

    @Override
    @Transactional
    public void alterarParcialmente(ColaboradorRequest request, Long colaboradorId) {
        var colaboradorOptional = repository.findById(colaboradorId);
        if (colaboradorOptional.isEmpty()) {
            throw new EntidadeNaoEncontradaException(ValidationMessage.COLABORADOR_NAO_ENCONTRADO, colaboradorId);
        }
        var colaborador = colaboradorOptional.get();
        var nomeJaExiste = repository.findByNome(request.getNome());
        var siglaJaExiste = repository.findBySigla(request.getSigla());
        if (nomeJaExiste.isPresent()) {
            if (nomeJaExiste.get().getId() != colaboradorId) {
                throw new NomeJaExisteException(String.format(ValidationMessage.O_NOME_JA_EXISTE, nomeJaExiste.get().getNome()));
            }
        }
        if (siglaJaExiste.isPresent()){
            if (siglaJaExiste.get().getId() != colaboradorId) {
                throw new NomeJaExisteException(String.format(ValidationMessage.A_SIGLA_JA_EXISTE, siglaJaExiste.get().getSigla()));
            }
        }

        if (StringUtils.hasText(request.getNome())) {
            colaborador.setNome(request.getNome());
        }

        if (StringUtils.hasText(request.getSigla())) {
            colaborador.setSigla(request.getSigla());
        }
        repository.save(colaborador);
    }
}
