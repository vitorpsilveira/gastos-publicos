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

    @Query("select c from Colaborador c where c.sigla in :siglas")
    List<Colaborador> findBySigla(List<String> siglas);

    @Query("SELECT new br.com.publico.gastos.domain.dto.response.GraficoAvaliacoesResponse(c.id, c.nome, c.sigla) " +
           "FROM Colaborador c " +
           "WHERE c.id in :id ")
    List<GraficoAvaliacoesResponse> findByIdIn(@Param("id") List<Long> id);
}