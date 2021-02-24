package br.com.publico.gastos.services.impl;

import br.com.publico.gastos.controller.request.AvaliacaoRequest;
import br.com.publico.gastos.domain.dto.mapper.AvaliacaoMapper;
import br.com.publico.gastos.repository.AvaliacaoRepository;
import br.com.publico.gastos.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AvaliacaoMapper avaliacaoMapper;

    @Override
    public void salvar(AvaliacaoRequest avaliacao) {
        avaliacaoRepository.save(avaliacaoMapper.avaliacaoRequestToEntity(avaliacao));
    }
}