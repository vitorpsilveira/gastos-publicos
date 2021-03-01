package br.com.publico.gastos.domain.dto.response;

import br.com.publico.gastos.domain.dto.AvaliacaoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraficoAvaliacoesResponse {

    private Long idColaborador;
    private String nomeSiglaColaborador;
    private List<AvaliacaoDTO> avaliacoes;

    public GraficoAvaliacoesResponse(Long idColaborador, String nomeColaborador, String siglaColaborador) {
        this.idColaborador = idColaborador;
        this.nomeSiglaColaborador = nomeColaborador.concat(" - ").concat(siglaColaborador);
    }
}
