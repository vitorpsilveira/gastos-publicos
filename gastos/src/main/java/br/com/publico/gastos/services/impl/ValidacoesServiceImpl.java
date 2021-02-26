package br.com.publico.gastos.services.impl;

import br.com.publico.gastos.domain.model.Avaliacao;
import br.com.publico.gastos.repository.AvaliacaoRepository;
import br.com.publico.gastos.services.ValidacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class ValidacoesServiceImpl implements ValidacoesService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Boolean verificaAvaliacaoExistente(String nome, String sigla, LocalDate data) {

        LocalDate dataInicial = LocalDate.now().withMonth(data.getMonthValue()).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate dataFinal = LocalDate.now().withMonth(data.getMonthValue()).with(TemporalAdjusters.lastDayOfMonth());

        List<Avaliacao> avaliacoes = avaliacaoRepository.findByColaboradorNomeAndColaboradorSiglaAndDataBetween(nome, sigla, dataInicial, dataFinal);

        return !avaliacoes.isEmpty();

    }

}
