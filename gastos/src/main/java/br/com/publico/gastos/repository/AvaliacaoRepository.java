package br.com.publico.gastos.repository;

import br.com.publico.gastos.domain.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findByColaboradorIdAndDataBetween(Long idColaborador, LocalDate dataInicial, LocalDate dataFinal);

    List<Avaliacao> findByColaboradorNomeAndColaboradorSiglaAndDataBetween(String nomeColaborador, String siglaColaborador, LocalDate dataInicial, LocalDate dataFinal);
}
