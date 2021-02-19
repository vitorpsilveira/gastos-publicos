package br.com.publico.gastos.repository;

import br.com.publico.gastos.domain.model.Municipio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios?orderBy=nome"
        , name = "municipios")
public interface MunicipioRepository {

    @GetMapping()
    List<Municipio> buscarMunicipios();
}
