package br.com.publico.gastos.services.impl;

import br.com.publico.gastos.controller.request.ColaboradorRequest;
import br.com.publico.gastos.domain.dto.AvaliacaoDTO;
import br.com.publico.gastos.domain.dto.mapper.ColaboradorMapper;
import br.com.publico.gastos.domain.dto.response.GraficoAvaliacoesResponse;
import br.com.publico.gastos.domain.model.Avaliacao;
import br.com.publico.gastos.repository.ColaboradorRepository;
import br.com.publico.gastos.services.AvaliacaoService;
import br.com.publico.gastos.services.ColaboradorService;
import br.com.publico.gastos.services.exception.ColaboradorPossuiAvaliacaoException;
import br.com.publico.gastos.services.exception.EntidadeNaoEncontradaException;
import br.com.publico.gastos.services.exception.NomeJaExisteException;
import br.com.publico.gastos.services.exception.SiglaJaExisteException;
import br.com.publico.gastos.services.message.ValidationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColaboradorServiceImpl implements ColaboradorService {

    @Autowired
    private ColaboradorRepository repository;

    @Autowired
    private ColaboradorMapper colaboradorMapper;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Override
    @Transactional
    public void salvar(ColaboradorRequest request) {
        var colaborador = colaboradorMapper.colaboradorRequestToEntity(request);
        var nomeJaExiste = repository.findByNome(colaborador.getNome());
        var siglaJaExiste = repository.findBySigla(colaborador.getSigla());

        if (nomeJaExiste.isPresent()) {
            throw new NomeJaExisteException(nomeJaExiste.get().getNome());
        }

        if (siglaJaExiste.isPresent()) {
            throw new SiglaJaExisteException(siglaJaExiste.get().getSigla());
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
                throw new NomeJaExisteException(nomeJaExiste.get().getNome());
            }
        }
        if (siglaJaExiste.isPresent()){
            if (siglaJaExiste.get().getId() != colaboradorId) {
                throw new SiglaJaExisteException(siglaJaExiste.get().getSigla());
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

    @Override
    @Transactional
    public void deletar(Long colaboradorId) {
        var colaboradorOptional = repository.findById(colaboradorId);
        if (colaboradorOptional.isEmpty()) {
            throw new EntidadeNaoEncontradaException(ValidationMessage.COLABORADOR_NAO_ENCONTRADO, colaboradorId);
        }
        var colaborador = colaboradorOptional.get();
        if (!colaborador.getAvaliacoes().isEmpty()) {
            throw new ColaboradorPossuiAvaliacaoException(colaborador.getNome());
        }
        repository.delete(colaborador);
    }

    @Override
    public List<GraficoAvaliacoesResponse> obterInformacoesGrafico(List<Long> idColaboradores) {
        List<GraficoAvaliacoesResponse> graficoAvaliacoesResponses = repository.findByIdIn(idColaboradores);

        graficoAvaliacoesResponses.forEach(avaliacoesResponse -> {
            avaliacoesResponse.setAvaliacoes(avaliacaoService.buscarAvaliacoesPorIdColaborador(avaliacoesResponse.getIdColaborador())
                    .stream().map(this::avaliacaoEntityToDTO)
                    .collect(Collectors.toList()));
        });

        return graficoAvaliacoesResponses;
    }

    private AvaliacaoDTO avaliacaoEntityToDTO(Avaliacao avaliacao) {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();

        avaliacaoDTO.setId(avaliacao.getId());
        avaliacaoDTO.setMesAno(avaliacao.getData().getMonthValue() + "/" + avaliacao.getData().getYear());
        avaliacaoDTO.setNota(avaliacao.getNota());
        avaliacaoDTO.setResultado(avaliacao.getResultado().getDescricao());

        return avaliacaoDTO;
    }
}
