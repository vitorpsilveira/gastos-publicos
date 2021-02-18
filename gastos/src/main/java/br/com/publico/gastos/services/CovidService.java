package br.com.publico.gastos.services;

import br.com.publico.gastos.domain.dto.response.GastosCovidResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CovidService {

    List<GastosCovidResponse> listGastosCovid(String anoMes, Integer pagina);
}
