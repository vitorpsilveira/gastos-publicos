package br.com.publico.gastos.services;

import br.com.publico.gastos.controller.request.AvaliacaoRequest;

public interface AvaliacaoService {

    void salvar(AvaliacaoRequest avaliacao);
}
