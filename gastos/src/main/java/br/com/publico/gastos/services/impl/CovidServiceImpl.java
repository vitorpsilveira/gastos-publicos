package br.com.publico.gastos.services.impl;

import br.com.publico.gastos.domain.dto.mapper.CovidMapper;
import br.com.publico.gastos.domain.dto.response.GastosCovidResponse;
import br.com.publico.gastos.domain.model.Covid19;
import br.com.publico.gastos.repository.CovidRepository;
import br.com.publico.gastos.services.CovidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CovidServiceImpl implements CovidService {

    private final CovidRepository covidRepository;
    private final CovidMapper covidMapper;

    @Override
    public List<GastosCovidResponse> listGastosCovid(String anoMes, Integer pagina) {
        List<Covid19> covid19List = covidRepository.buscarDadosCovid(anoMes, pagina, "cd628a98add0946165e28dc947665a90");

        return covid19List.stream()
                .map(covidMapper::toResponse)
                .collect(Collectors.toList());
    }
}
