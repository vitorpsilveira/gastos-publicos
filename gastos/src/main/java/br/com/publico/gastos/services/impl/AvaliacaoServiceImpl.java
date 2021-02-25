package br.com.publico.gastos.services.impl;


import br.com.publico.gastos.ExcelHelper;
import br.com.publico.gastos.domain.model.Avaliacao;
import br.com.publico.gastos.repository.AvaliacaoRepository;
import br.com.publico.gastos.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import br.com.publico.gastos.controller.request.AvaliacaoRequest;
import br.com.publico.gastos.controller.request.AvaliacaoUpdateRequest;
import br.com.publico.gastos.domain.dto.mapper.AvaliacaoMapper;
import br.com.publico.gastos.domain.dto.response.AvaliacaoResponse;
import br.com.publico.gastos.domain.model.Avaliacao;
import br.com.publico.gastos.domain.model.Status;
import br.com.publico.gastos.domain.model.TipoAvaliacao;
import br.com.publico.gastos.domain.model.TipoResultado;
import br.com.publico.gastos.repository.AvaliacaoRepository;
import br.com.publico.gastos.services.AvaliacaoService;
import br.com.publico.gastos.services.exception.ColaboradorPossuiAvaliacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AvaliacaoMapper avaliacaoMapper;

    @Override
    public void save(MultipartFile file) {
        try {
            final List<Avaliacao> avaliacaoList = ExcelHelper.excelToAvaliacao(file.getInputStream());
            avaliacaoRepository.saveAll(avaliacaoList);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao armazenar dados do Excel: " + e.getMessage());
        }
    }

    @Override
    public List<Avaliacao> getAllAvaliacoes() {
        return avaliacaoRepository.findAll();
    }


    private Boolean existeAvaliacaoMesColaborador(Long idColaborador, LocalDate data) {
        LocalDate dataInicial = LocalDate.now().withMonth(data.getMonthValue()).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate dataFinal = LocalDate.now().withMonth(data.getMonthValue()).with(TemporalAdjusters.lastDayOfMonth());

        List<Avaliacao> avaliacoes = avaliacaoRepository.findByColaboradorIdAndDataBetween(idColaborador, dataInicial, dataFinal);

        return !avaliacoes.isEmpty();
    }

    @Override
    public void salvar(AvaliacaoRequest avaliacaoRequest) {
        Avaliacao avaliacao = avaliacaoMapper.avaliacaoRequestToEntity(avaliacaoRequest);

        if (!existeAvaliacaoMesColaborador(avaliacao.getColaborador().getId(), avaliacaoRequest.getData())) {
            avaliacaoRepository.save(avaliacao);
        }
        else {
            throw new ColaboradorPossuiAvaliacaoException(avaliacaoRequest.getData().getMonthValue());
        }
    }

    private Avaliacao montaAvaliacao(Long id, AvaliacaoUpdateRequest avaliacaoRequest) {
        Avaliacao avaliacao = avaliacaoRepository.getOne(id);

        if (Objects.nonNull(avaliacaoRequest.getTipoAvaliacao())) {
            avaliacao.setTipoAvaliacao(TipoAvaliacao.fromShort(avaliacaoRequest.getTipoAvaliacao()));
        }

        if (Objects.nonNull(avaliacaoRequest.getResultado())) {
            avaliacao.setResultado(TipoResultado.fromShort(avaliacaoRequest.getResultado()));
        }

        if (Objects.nonNull(avaliacaoRequest.getStatus())) {
            avaliacao.setStatus(Status.fromShort(avaliacaoRequest.getStatus()));
        }

        if (Objects.nonNull(avaliacaoRequest.getData())) {
            avaliacao.setData(avaliacaoRequest.getData());
        }

        if (Objects.nonNull(avaliacaoRequest.getNota())) {
            avaliacao.setNota(avaliacaoRequest.getNota());
        }

        return avaliacao;
    }

    @Override
    public void atualizar(Long id, AvaliacaoUpdateRequest avaliacaoRequest) {
        avaliacaoRepository.save(montaAvaliacao(id, avaliacaoRequest));
    }

    @Override
    public List<AvaliacaoResponse> buscarAvaliacoes() {
        return avaliacaoRepository.findAll()
                .stream().map(avaliacaoMapper::avaliacaoEntityToResponse)
                .collect(Collectors.toList());
    }
}
