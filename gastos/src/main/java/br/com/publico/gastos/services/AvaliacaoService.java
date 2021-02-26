package br.com.publico.gastos.services;


import br.com.publico.gastos.domain.model.Avaliacao;
import org.springframework.web.multipart.MultipartFile;
import br.com.publico.gastos.controller.request.AvaliacaoRequest;
import br.com.publico.gastos.controller.request.AvaliacaoUpdateRequest;
import br.com.publico.gastos.domain.dto.response.AvaliacaoResponse;

import java.util.List;


public interface AvaliacaoService {

    void save(MultipartFile file);

    List<Avaliacao> getAllAvaliacoes();

    void salvar(AvaliacaoRequest avaliacao);

    void atualizar(Long id, AvaliacaoUpdateRequest avaliacao);

    List<AvaliacaoResponse> buscarAvaliacoes();

    void deletar(Long avaliacaoId);
}
