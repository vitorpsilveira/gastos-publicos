package br.com.publico.gastos.services.impl;

import br.com.publico.gastos.ExcelHelper;
import br.com.publico.gastos.domain.model.Avaliacao;
import br.com.publico.gastos.repository.AvaliacaoRepository;
import br.com.publico.gastos.services.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class ExcelServiceImpl implements ExcelService {

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Override
    public void save(MultipartFile file) {
        try {
            List<Avaliacao> avaliacaoList = ExcelHelper.excelToAvaliacao(file.getInputStream());
            avaliacaoRepository.saveAll(avaliacaoList);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao armazenar dados do Excel: " + e.getMessage());
        }
    }

    @Override
    public List<Avaliacao> getAllAvaliacoes() {
        return avaliacaoRepository.findAll();
    }
}
