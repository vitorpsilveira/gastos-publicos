package br.com.publico.gastos.repository;

import br.com.publico.gastos.domain.model.Covid19;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "http://www.portaltransparencia.gov.br/api-de-dados/coronavirus/movimento-liquido-despesa"
        , name = "covid")
public interface CovidRepository {

    @GetMapping()
    List<Covid19> buscarDadosCovid(@RequestParam String mesAnoLancamento, @RequestParam(required = false) Integer pagina,
                                   @RequestHeader("chave-api-dados") String chave);

}
