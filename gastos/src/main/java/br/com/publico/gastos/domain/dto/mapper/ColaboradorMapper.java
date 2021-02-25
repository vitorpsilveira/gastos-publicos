package br.com.publico.gastos.domain.dto.mapper;

import br.com.publico.gastos.controller.request.ColaboradorRequest;
import br.com.publico.gastos.domain.dto.response.ColaboradorResponse;
import br.com.publico.gastos.domain.model.Colaborador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Objects;

@Mapper(componentModel = "spring", imports = Objects.class)
public interface ColaboradorMapper {

    Colaborador colaboradorRequestToEntity(ColaboradorRequest request);

    @Mapping(target = "temAvaliacao", expression = "java(Objects.nonNull(colaborador.getAvaliacoes()) && !colaborador.getAvaliacoes().isEmpty())")
    ColaboradorResponse colaboradorEntityToResponse(Colaborador colaborador);
}
