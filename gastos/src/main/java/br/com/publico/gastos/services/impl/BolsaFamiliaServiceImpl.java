package br.com.publico.gastos.services.impl;

import br.com.publico.gastos.domain.dto.mapper.BolsaFamiliaMapper;
import br.com.publico.gastos.domain.dto.response.GastosBolsaFamiliaResponse;
import br.com.publico.gastos.domain.model.BolsaFamilia;
import br.com.publico.gastos.repository.BolsaFamiliaRepository;
import br.com.publico.gastos.services.BolsaFamiliaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BolsaFamiliaServiceImpl implements BolsaFamiliaService {

    private final BolsaFamiliaRepository bolsaFamiliaRepository;
    private final BolsaFamiliaMapper bolsaFamiliaMapper;

    @Override
    public List<GastosBolsaFamiliaResponse> listBolsaFamilia(String anoMes, String codigoIbge) {
        List<BolsaFamilia> covid19List = bolsaFamiliaRepository.buscarBolsaFamilia(anoMes, codigoIbge, "cd628a98add0946165e28dc947665a90");

        return covid19List.stream()
                .map(bolsaFamiliaMapper::toResponse)
                .collect(Collectors.toList());
    }
}
