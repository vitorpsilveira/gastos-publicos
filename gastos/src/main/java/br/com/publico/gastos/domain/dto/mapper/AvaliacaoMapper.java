package br.com.publico.gastos.domain.dto.mapper;

import br.com.publico.gastos.controller.request.AvaliacaoRequest;
import br.com.publico.gastos.domain.dto.response.AvaliacaoResponse;
import br.com.publico.gastos.domain.model.Avaliacao;
import br.com.publico.gastos.domain.model.Colaborador;
import br.com.publico.gastos.domain.model.Status;
import br.com.publico.gastos.domain.model.TipoAvaliacao;
import br.com.publico.gastos.domain.model.TipoResultado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {

    default AvaliacaoResponse avaliacaoEntityToResponse(Avaliacao avaliacao) {
        AvaliacaoResponse avaliacaoResponse = new AvaliacaoResponse();

        avaliacaoResponse.setId(avaliacao.getId());
        avaliacaoResponse.setColaborador(avaliacao.getColaborador());
        avaliacaoResponse.setTipoAvaliacao(avaliacao.getTipoAvaliacao().getDescricao());
        avaliacaoResponse.setResultado(avaliacao.getResultado().getDescricao());
        avaliacaoResponse.setStatus(avaliacao.getStatus().getDescricao());
        avaliacaoResponse.setData(avaliacao.getData().toString());
        avaliacaoResponse.setNota(avaliacao.getNota());

        return avaliacaoResponse;
    }

    default Avaliacao avaliacaoRequestToEntity(AvaliacaoRequest request) {
        Avaliacao avaliacao = new Avaliacao();
        Colaborador colaborador = new Colaborador();
        colaborador.setId(request.getColaborador());

        avaliacao.setColaborador(colaborador);
        avaliacao.setTipoAvaliacao(TipoAvaliacao.fromShort(request.getTipoAvaliacao()));
        avaliacao.setResultado(TipoResultado.fromShort(request.getResultado()));
        avaliacao.setStatus(Status.fromShort(request.getStatus()));
        avaliacao.setData(request.getData());
        avaliacao.setNota(request.getNota());

        return avaliacao;
    }
}
