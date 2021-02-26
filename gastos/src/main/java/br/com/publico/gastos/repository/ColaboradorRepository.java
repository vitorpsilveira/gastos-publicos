package br.com.publico.gastos.repository;

import br.com.publico.gastos.domain.dto.response.GraficoAvaliacoesResponse;
import br.com.publico.gastos.domain.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    Optional<Colaborador> findByNome(String nome);
    Optional<Colaborador> findBySigla(String sigla);

    @Query("SELECT new br.com.publico.gastos.domain.dto.response.GraficoAvaliacoesResponse(c.id, c.nome, c.sigla, c.avaliacoes) " +
           "FROM Colaborador c " +
           "LEFT JOIN Avaliacao a ON a.colaborador.id = c.id " +
           "WHERE c.id in :id ")
    List<GraficoAvaliacoesResponse> findByIdIn(@Param("id") List<Long> id);
}