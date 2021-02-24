package br.com.publico.gastos.services;

import br.com.publico.gastos.domain.model.Avaliacao;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface AvaliacaoService {

    void save(MultipartFile file);

    List<Avaliacao> getAllAvaliacoes();
}
