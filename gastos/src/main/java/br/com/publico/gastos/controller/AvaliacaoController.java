package br.com.publico.gastos.controller;

import br.com.publico.gastos.controller.exceptionhandler.Problema;
import br.com.publico.gastos.controller.request.AvaliacaoRequest;
import br.com.publico.gastos.controller.request.AvaliacaoUpdateRequest;
import br.com.publico.gastos.controller.swagger.SwaggerApiMessage;
import br.com.publico.gastos.controller.swagger.SwaggerApiStatusCode;
import br.com.publico.gastos.domain.dto.response.AvaliacaoResponse;
import br.com.publico.gastos.services.AvaliacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    @ApiOperation(value = "Buscar todos os colaboradores")
    public List<AvaliacaoResponse> getAll() {
        return avaliacaoService.buscarAvaliacoes();
    }

    @PostMapping
    @ApiOperation(value = "Salvar avaliação")
    @ApiResponses(value = { @ApiResponse(code = SwaggerApiStatusCode.CODE_200, message = "Salvar e retornar status 200"),
            @ApiResponse(code = SwaggerApiStatusCode.CODE_400, message = SwaggerApiMessage.REQUISICAO_INVALIDA, response = Problema.class)})
    public ResponseEntity<Void> save(@RequestBody @Valid AvaliacaoRequest avaliacao) {
        avaliacaoService.salvar(avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Editar avaliação")
    @ApiResponses(value = { @ApiResponse(code = SwaggerApiStatusCode.CODE_200, message = "Editar e retornar status 200"),
            @ApiResponse(code = SwaggerApiStatusCode.CODE_400, message = SwaggerApiMessage.REQUISICAO_INVALIDA, response = Problema.class)})
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid AvaliacaoUpdateRequest avaliacao) {
        avaliacaoService.atualizar(id, avaliacao);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}