package br.com.publico.gastos.domain.dto.mapper;

import br.com.publico.gastos.domain.dto.response.ConsultaMunicipioResponse;
import br.com.publico.gastos.domain.model.Municipio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MunicipioMapper {

    ConsultaMunicipioResponse toResponse(Municipio municipio);
}
