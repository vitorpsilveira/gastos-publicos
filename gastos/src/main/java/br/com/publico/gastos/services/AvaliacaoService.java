package br.com.publico.gastos.services;

import br.com.publico.gastos.controller.request.AvaliacaoRequest;
import br.com.publico.gastos.controller.request.AvaliacaoUpdateRequest;

public interface AvaliacaoService {

    void salvar(AvaliacaoRequest avaliacao);

    void atualizar(Long id, AvaliacaoUpdateRequest avaliacao);
}
