package br.com.publico.gastos.controller;

import br.com.publico.gastos.ExcelHelper;
import br.com.publico.gastos.domain.dto.response.MensagemResponse;
import br.com.publico.gastos.services.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/upload")
    public ResponseEntity<MensagemResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        String mensagem = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                avaliacaoService.save(file);

                mensagem = "Arquivo recebido com sucesso: " + file.getOriginalFilename();
                return  ResponseEntity.status(HttpStatus.OK).body(new MensagemResponse(mensagem));
            } catch (Exception e) {
                mensagem = "Não foi possivel subir o arquivo: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MensagemResponse(mensagem));
            }
        }

        mensagem = "Por favor, adicione um arquivo Excel!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemResponse(mensagem));
    }
}
