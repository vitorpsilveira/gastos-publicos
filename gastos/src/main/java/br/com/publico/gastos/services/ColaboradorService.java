package br.com.publico.gastos.services;

import br.com.publico.gastos.controller.request.ColaboradorRequest;

public interface ColaboradorService {

    void salvar(ColaboradorRequest request);

    void alterarParcialmente(ColaboradorRequest request, Long colaboradorId);
}
