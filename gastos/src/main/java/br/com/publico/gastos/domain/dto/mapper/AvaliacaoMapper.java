package br.com.publico.gastos.domain.dto.mapper;

import br.com.publico.gastos.controller.request.AvaliacaoRequest;
import br.com.publico.gastos.domain.model.Avaliacao;
import br.com.publico.gastos.domain.model.Colaborador;
import br.com.publico.gastos.domain.model.Status;
import br.com.publico.gastos.domain.model.TipoAvaliacao;
import br.com.publico.gastos.domain.model.TipoResultado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {

    default Avaliacao avaliacaoRequestToEntity(AvaliacaoRequest request) {
        Avaliacao avaliacao = new Avaliacao();
        Colaborador colaborador = new Colaborador();
        colaborador.setId(request.getColaborador());

        avaliacao.setColaborador(colaborador);
        avaliacao.setTipoAvaliacao(TipoAvaliacao.fromString(request.getTipoAvaliacao()));
        avaliacao.setResultado(TipoResultado.fromString(request.getResultado()));
        avaliacao.setStatus(Status.fromString(request.getStatus()));
        avaliacao.setData(request.getData());
        avaliacao.setNota(request.getNota());

        return avaliacao;
    }
}
