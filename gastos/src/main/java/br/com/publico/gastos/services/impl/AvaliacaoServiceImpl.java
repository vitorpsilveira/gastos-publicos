package br.com.publico.gastos.services.impl;


import br.com.publico.gastos.ExcelHelper;
import br.com.publico.gastos.controller.request.AvaliacaoRequest;
import br.com.publico.gastos.controller.request.AvaliacaoUpdateRequest;
import br.com.publico.gastos.domain.dto.mapper.AvaliacaoMapper;
import br.com.publico.gastos.domain.dto.response.AvaliacaoResponse;
import br.com.publico.gastos.domain.model.Avaliacao;
import br.com.publico.gastos.domain.model.Colaborador;
import br.com.publico.gastos.domain.model.Status;
import br.com.publico.gastos.domain.model.TipoAvaliacao;
import br.com.publico.gastos.domain.model.TipoResultado;
import br.com.publico.gastos.repository.AvaliacaoRepository;
import br.com.publico.gastos.repository.ColaboradorRepository;
import br.com.publico.gastos.services.AvaliacaoService;
import br.com.publico.gastos.services.exception.ColaboradorPossuiAvaliacaoException;
import br.com.publico.gastos.services.exception.EntidadeNaoEncontradaException;
import br.com.publico.gastos.services.message.ValidationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ExcelHelper excelHelper;

    @Autowired
    private AvaliacaoMapper avaliacaoMapper;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Override
    @Transactional
    public void save(MultipartFile file) {
        try {
            final List<Avaliacao> avaliacaoList = excelHelper.excelToAvaliacao(file.getInputStream());
            var siglas = avaliacaoList.stream().map(av -> av.getColaborador().getSigla()).distinct().collect(Collectors.toList());
            final List<Colaborador> auxColaboradoresExcel = avaliacaoList.stream().map(Avaliacao::getColaborador).collect(Collectors.toList());
            final List<Colaborador> colaboradoresExcel = new ArrayList<>();

            for(Colaborador elemento : auxColaboradoresExcel) {
                if (colaboradoresExcel.isEmpty()) {
                    colaboradoresExcel.add(elemento);
                } else {
                    var hasOne = colaboradoresExcel.stream()
                            .filter(c -> c.getSigla().equals(elemento.getSigla()))
                            .findAny()
                            .orElse(null);
                    if (Objects.isNull(hasOne)) {
                        colaboradoresExcel.add(elemento);
                    }
                }
            }

            var colaboradoresSiglasExistentes = colaboradorRepository.findBySigla(siglas);
            if (colaboradoresSiglasExistentes.isEmpty()) {
                colaboradorRepository.saveAll(colaboradoresExcel);
            } else {
                for(Colaborador colaboradores : colaboradoresExcel){
                    if(colaboradorRepository.findBySigla(colaboradores.getSigla()).isEmpty()) {
                        colaboradorRepository.save(colaboradores);
                    }

                }
            }

            var colaboradoresSalvos = colaboradorRepository.findBySigla(siglas);
            avaliacaoList.forEach(a -> a.setColaborador(colaboradoresSalvos.stream()
                    .filter(f -> f.getSigla().toUpperCase().equals(a.getColaborador().getSigla().toUpperCase()))
                    .findAny()
                    .orElse(a.getColaborador())
            ));

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
    @Transactional
    public void salvar(AvaliacaoRequest avaliacaoRequest) {
        Avaliacao avaliacao = avaliacaoMapper.avaliacaoRequestToEntity(avaliacaoRequest);

        Optional<Colaborador> colaborador = colaboradorRepository.findById(avaliacaoRequest.getColaborador());

        if (colaborador.isEmpty()) {
            throw new EntidadeNaoEncontradaException(ValidationMessage.COLABORADOR_NAO_ENCONTRADO, avaliacaoRequest.getColaborador());
        }

        avaliacao.setColaborador(colaboradorRepository.findById(avaliacaoRequest.getColaborador()).get());

        if (!existeAvaliacaoMesColaborador(avaliacao.getColaborador().getId(), avaliacaoRequest.getData())) {
            avaliacaoRepository.save(avaliacao);
        } else {
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

    @Override
    @Transactional
    public void deletar(Long avaliacaoId) {
        var avaliacaoOptional = avaliacaoRepository.findById(avaliacaoId);
        if (avaliacaoOptional.isEmpty()) {
            throw new EntidadeNaoEncontradaException(ValidationMessage.AVALICAO_NAO_ENCONTRADA, avaliacaoId);
        }
        var avaliacao = avaliacaoOptional.get();
        avaliacaoRepository.delete(avaliacao);
    }

    @Override
    public List<Avaliacao> buscarAvaliacoesPorIdColaborador(Long id) {
        return avaliacaoRepository.findByColaboradorIdOrderByData(id);
    }
}
