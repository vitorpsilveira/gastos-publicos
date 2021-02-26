package br.com.publico.gastos.repository;

import br.com.publico.gastos.domain.model.Avaliacao;
import br.com.publico.gastos.domain.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    Optional<Colaborador> findByNome(String nome);
    Optional<Colaborador> findBySigla(String sigla);

    @Query("select c from Colaborador c where c.sigla in :siglas")
    List<Colaborador> findBySigla(List<String> siglas);

}