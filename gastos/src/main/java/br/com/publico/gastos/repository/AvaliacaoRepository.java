package br.com.publico.gastos.repository;

import br.com.publico.gastos.domain.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a " +
           "FROM Avaliacao a " +
           "WHERE a.colaborador.id = :idColaborador " +
           "AND a.data BETWEEN :dataInicial AND :dataFinal " +
           "AND a.id <> :idAvaliacao ")
    List<Avaliacao> findByColaboradorIdAndDataBetween(@Param("idColaborador") Long idColaborador, @Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal, @Param("idAvaliacao") Long idAvaliacao);

    List<Avaliacao> findByColaboradorIdAndDataBetween(Long idColaborador, LocalDate dataInicial, LocalDate dataFinal);

    List<Avaliacao> findByColaboradorNomeAndColaboradorSiglaAndDataBetween(String nomeColaborador, String siglaColaborador, LocalDate dataInicial, LocalDate dataFinal);

    List<Avaliacao> findByColaboradorIdOrderByData(Long id);
}
