package br.com.publico.gastos.services;

import br.com.publico.gastos.domain.dto.response.ConsultaMunicipioResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MunicipioService {

    List<ConsultaMunicipioResponse> listaMunicipios();

}
