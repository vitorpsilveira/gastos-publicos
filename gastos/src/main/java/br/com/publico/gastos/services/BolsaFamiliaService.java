package br.com.publico.gastos.services;

import br.com.publico.gastos.domain.dto.response.GastosBolsaFamiliaResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BolsaFamiliaService {

    List<GastosBolsaFamiliaResponse> listBolsaFamilia(String anoMes,  String codigoIbge);
}
