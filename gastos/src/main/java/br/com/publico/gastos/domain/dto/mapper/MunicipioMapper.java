package br.com.publico.gastos.domain.dto.mapper;

import br.com.publico.gastos.domain.dto.response.ConsultaMunicipioResponse;
import br.com.publico.gastos.domain.model.Municipio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MunicipioMapper {

    @Mapping(target = "uf", source = "microrregiao.mesorregiao.uf.sigla")
    ConsultaMunicipioResponse toResponse(Municipio municipio);
}
