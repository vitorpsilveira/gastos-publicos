package br.com.publico.gastos.controller;

import br.com.publico.gastos.controller.exceptionhandler.Problema;
import br.com.publico.gastos.controller.request.ColaboradorRequest;
import br.com.publico.gastos.controller.swagger.SwaggerApiMessage;
import br.com.publico.gastos.controller.swagger.SwaggerApiStatusCode;
import br.com.publico.gastos.domain.dto.mapper.ColaboradorMapper;
import br.com.publico.gastos.domain.dto.response.ColaboradorResponse;
import br.com.publico.gastos.repository.ColaboradorRepository;
import br.com.publico.gastos.services.ColaboradorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("colaborador")
public class ColaboradorController {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ColaboradorService colaboradorService;

    @Autowired
    private ColaboradorMapper colaboradorMapper;

    @GetMapping
    @ApiOperation(value = "Buscar todos os colaboradores")
    public List<ColaboradorResponse> getAll() {
        return colaboradorRepository.findAll()
                .stream().map(colaboradorMapper::colaboradorEntityToResponse)
                .collect(Collectors.toList());
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
    @ApiResponses(value = { @ApiResponse(code = SwaggerApiStatusCode.CODE_204, message = "Deletado com sucesso e retornar status 204"),
        @ApiResponse(code = SwaggerApiStatusCode.CODE_400, message = SwaggerApiMessage.REQUISICAO_INVALIDA, response = Problema.class)})
    public ResponseEntity<Void> deletar(@PathVariable Long colaboradorId) {
        colaboradorService.deletar(colaboradorId);
        return ResponseEntity.noContent().build();
    }
}
