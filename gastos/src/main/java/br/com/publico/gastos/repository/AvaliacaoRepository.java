package br.com.publico.gastos.repository;

import br.com.publico.gastos.domain.model.Avaliacao;
import br.com.publico.gastos.domain.model.AvaliacaoTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<AvaliacaoTemplate, Long> {
}
