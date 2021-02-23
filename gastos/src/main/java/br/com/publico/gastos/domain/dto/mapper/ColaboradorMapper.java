package br.com.publico.gastos.domain.dto.mapper;

import br.com.publico.gastos.controller.request.ColaboradorRequest;
import br.com.publico.gastos.domain.model.Colaborador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper {

    Colaborador colaboradorRequestToEntity(ColaboradorRequest request);
}
