package br.com.publico.gastos.services;

import br.com.publico.gastos.controller.request.AvaliacaoRequest;
import br.com.publico.gastos.controller.request.AvaliacaoUpdateRequest;
import br.com.publico.gastos.domain.dto.response.AvaliacaoResponse;

import java.util.List;

public interface AvaliacaoService {

    void salvar(AvaliacaoRequest avaliacao);

    void atualizar(Long id, AvaliacaoUpdateRequest avaliacao);

    List<AvaliacaoResponse> buscarAvaliacoes();
}
