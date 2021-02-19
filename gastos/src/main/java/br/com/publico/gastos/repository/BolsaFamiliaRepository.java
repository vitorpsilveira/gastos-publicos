package br.com.publico.gastos.repository;

import br.com.publico.gastos.domain.model.BolsaFamilia;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "www.portaltransparencia.gov.br/api-de-dados/bolsa-familia-por-municipio"
        , name = "BolsaFamilia")
public interface BolsaFamiliaRepository {

    @GetMapping()
    List<BolsaFamilia> buscarBolsaFamilia(@RequestParam String mesAno, @RequestParam String codigoIbge,
                                        @RequestHeader("chave-api-dados") String chave);
}
