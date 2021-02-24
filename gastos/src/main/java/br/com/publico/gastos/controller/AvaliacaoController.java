package br.com.publico.gastos.controller;

import br.com.publico.gastos.controller.request.AvaliacaoRequest;
import br.com.publico.gastos.controller.swagger.SwaggerApiMessage;
import br.com.publico.gastos.controller.swagger.SwaggerApiStatusCode;
import br.com.publico.gastos.services.AvaliacaoService;
import br.com.publico.gastos.services.exception.DomainException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    @ApiOperation(value = "Salvar avaliação")
    @ApiResponses(value = { @ApiResponse(code = SwaggerApiStatusCode.CODE_200, message = "Salvar e retornar status 200"),
            @ApiResponse(code = SwaggerApiStatusCode.CODE_400, message = SwaggerApiMessage.REQUISICAO_INVALIDA, response = DomainException.class)})
    public ResponseEntity<?> save(@RequestBody @Valid AvaliacaoRequest avaliacao) {
        avaliacaoService.salvar(avaliacao);
        return ResponseEntity.ok().build();
    }

}
