package br.com.publico.gastos.services.impl;

import br.com.publico.gastos.domain.dto.mapper.BolsaFamiliaMapper;
import br.com.publico.gastos.domain.dto.response.GastosBolsaFamiliaResponse;
import br.com.publico.gastos.domain.model.BolsaFamilia;
import br.com.publico.gastos.repository.BolsaFamiliaRepository;
import br.com.publico.gastos.services.BolsaFamiliaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BolsaFamiliaServiceImpl implements BolsaFamiliaService {

    private final BolsaFamiliaRepository bolsaFamiliaRepository;
    private final BolsaFamiliaMapper bolsaFamiliaMapper;

    @Override
    public List<GastosBolsaFamiliaResponse> listBolsaFamilia(String anoMes, String codigoIbge) {
        List<BolsaFamilia> covid19List = new ArrayList<>();

        List<String> codigosIbge = Arrays.stream(codigoIbge.split(",")).distinct().collect(Collectors.toList());

        for (String codigoIbgeSeparado: codigosIbge) {
            covid19List.add(bolsaFamiliaRepository.buscarBolsaFamilia
                    (anoMes, codigoIbgeSeparado, "cd628a98add0946165e28dc947665a90").get(0));
        }

        return covid19List.stream()
                .map(bolsaFamiliaMapper::toResponse)
                .collect(Collectors.toList());
    }
}
