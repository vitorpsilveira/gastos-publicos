package br.com.publico.gastos.domain.dto.mapper;

import br.com.publico.gastos.domain.dto.response.GastosBolsaFamiliaResponse;
import br.com.publico.gastos.domain.model.BolsaFamilia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BolsaFamiliaMapper {

    @Mapping(target = "municipio.uf", source = "municipio.uf.sigla")
    GastosBolsaFamiliaResponse toResponse(BolsaFamilia bolsaFamilia);
}
