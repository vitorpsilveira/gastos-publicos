package br.com.publico.gastos.services;

import br.com.publico.gastos.domain.model.Avaliacao;
import br.com.publico.gastos.domain.model.AvaliacaoTemplate;
import br.com.publico.gastos.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ExcelService {

    void save(MultipartFile file);

    List<Avaliacao> getAllAvaliacoes();
}
