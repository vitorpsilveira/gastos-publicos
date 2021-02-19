package br.com.publico.gastos.services.impl;

import br.com.publico.gastos.domain.dto.mapper.MunicipioMapper;
import br.com.publico.gastos.domain.dto.response.ConsultaMunicipioResponse;
import br.com.publico.gastos.domain.model.Municipio;
import br.com.publico.gastos.repository.MunicipioRepository;
import br.com.publico.gastos.services.MunicipioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MunicipioServiceImpl implements MunicipioService {

    private final MunicipioRepository municipioRepository;
    private final MunicipioMapper municipioMapper;

    @Override
    public List<ConsultaMunicipioResponse> listaMunicipios() {
        List<Municipio> municipioList = municipioRepository.buscarMunicipios();

        return municipioList.stream()
                .map(municipioMapper::toResponse)
                .collect(Collectors.toList());
    }

}
