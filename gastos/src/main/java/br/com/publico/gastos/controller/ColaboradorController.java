package br.com.publico.gastos.controller;

import br.com.publico.gastos.controller.exceptionhandler.Problema;
import br.com.publico.gastos.controller.request.ColaboradorRequest;
import br.com.publico.gastos.controller.swagger.SwaggerApiMessage;
import br.com.publico.gastos.controller.swagger.SwaggerApiStatusCode;
import br.com.publico.gastos.domain.model.Colaborador;
import br.com.publico.gastos.repository.ColaboradorRepository;
import br.com.publico.gastos.services.ColaboradorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("colaborador")
public class ColaboradorController {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    public List<Colaborador> getAll() {
        return colaboradorRepository.findAll();
    }

    @PostMapping
    @ApiOperation(value = "Salvar colaborador")
    @ApiResponses(value = { @ApiResponse(code = SwaggerApiStatusCode.CODE_200, message = "Salvar e retornar status 200"),
        @ApiResponse(code = SwaggerApiStatusCode.CODE_400, message = SwaggerApiMessage.REQUISICAO_INVALIDA, response = Problema.class)})
    public ResponseEntity<?> salvar(@RequestBody @Valid ColaboradorRequest request) {
        colaboradorService.salvar(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{colaboradorId}")
    @ApiOperation(value = "Alterar o nome e a sigla do colaborador")
    @ApiResponses(value = { @ApiResponse(code = SwaggerApiStatusCode.CODE_200, message = "Alterar e retornar status 200"),
        @ApiResponse(code = SwaggerApiStatusCode.CODE_400, message = SwaggerApiMessage.REQUISICAO_INVALIDA, response = Problema.class)})
    public ResponseEntity<?> alterarParcialmente(@RequestBody ColaboradorRequest request, @PathVariable Long colaboradorId) {
        colaboradorService.alterarParcialmente(request, colaboradorId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{colaboradorId}")
    @ApiOperation(value = "Deletar usuário se não houver associações vinculadas a ele")
    @ApiResponses(value = { @ApiResponse(code = SwaggerApiStatusCode.CODE_200, message = "Deletado com sucesso e retornar status 204"),
        @ApiResponse(code = SwaggerApiStatusCode.CODE_400, message = SwaggerApiMessage.REQUISICAO_INVALIDA, response = Problema.class)})
    public ResponseEntity deletar(@PathVariable Long colaboradorId) {
        colaboradorService.deletar(colaboradorId);
        return ResponseEntity.noContent().build();
    }
}
