package br.com.publico.gastos.services;

import br.com.publico.gastos.controller.request.ColaboradorRequest;
import br.com.publico.gastos.domain.dto.response.GraficoAvaliacoesResponse;

import java.util.List;

public interface ColaboradorService {

    void salvar(ColaboradorRequest request);

    void alterarParcialmente(ColaboradorRequest request, Long colaboradorId);

    void deletar(Long colaboradorId);

    List<GraficoAvaliacoesResponse> obterInformacoesGrafico(List<Long> idColaboradores);
}
