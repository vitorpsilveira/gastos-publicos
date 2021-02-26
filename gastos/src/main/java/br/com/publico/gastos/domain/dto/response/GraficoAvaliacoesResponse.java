package br.com.publico.gastos.domain.dto.response;

import br.com.publico.gastos.domain.dto.AvaliacaoDTO;
import br.com.publico.gastos.domain.model.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraficoAvaliacoesResponse {

    private Long idColaborador;
    private String nomeSiglaColaborador;
    private List<AvaliacaoDTO> avaliacoes;

    public GraficoAvaliacoesResponse(Long idColaborador, String nomeColaborador, String siglaColaborador, Collection<Avaliacao> avaliacoes) {
        this.idColaborador = idColaborador;
        this.nomeSiglaColaborador = nomeColaborador.concat(" - ").concat(siglaColaborador);
        this.avaliacoes = avaliacoes.stream().map(this::avaliacaoEntityToDTO).collect(Collectors.toList());
    }

    private AvaliacaoDTO avaliacaoEntityToDTO(Avaliacao avaliacao) {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();

        avaliacaoDTO.setId(avaliacao.getId());
        avaliacaoDTO.setMesAno(formataMesAnoAvaliacao(avaliacao.getData()));
        avaliacaoDTO.setNota(avaliacao.getNota());
        avaliacaoDTO.setResultado(avaliacao.getResultado().getDescricao());

        return avaliacaoDTO;
    }

    private String formataMesAnoAvaliacao(LocalDate data) {
        String mesAno;

        DateFormat df = new SimpleDateFormat("MMMMM", new Locale("pt", "BR"));

        mesAno = StringUtils.substring(df.format(data), 0, 2);
        mesAno.concat("/").concat(Integer.toString(data.getYear()));

        return mesAno;
    }
}
