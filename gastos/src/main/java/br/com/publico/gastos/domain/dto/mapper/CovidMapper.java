package br.com.publico.gastos.domain.dto.mapper;

import br.com.publico.gastos.domain.dto.response.GastosCovidResponse;
import br.com.publico.gastos.domain.model.Covid19;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CovidMapper {

    GastosCovidResponse toResponse(Covid19 covid19);
}
