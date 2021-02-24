package br.com.publico.gastos.controller;

import br.com.publico.gastos.controller.request.ColaboradorRequest;
import br.com.publico.gastos.domain.dto.mapper.ColaboradorMapper;
import br.com.publico.gastos.domain.dto.response.ColaboradorResponse;
import br.com.publico.gastos.repository.ColaboradorRepository;
import br.com.publico.gastos.services.ColaboradorService;
import br.com.publico.gastos.services.exception.DomainException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<ColaboradorResponse> getAll() {
        return colaboradorRepository.findAll()
                .stream().map(colaboradorMapper::colaboradorEntityToResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ApiOperation(value = "Salvar colaborador")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Salvar e retornar status 200"),
        @ApiResponse(code = 400, message = "Requisição inválida! Erro de validação", response = DomainException.class)})
    public ResponseEntity<?> save(@RequestBody @Valid ColaboradorRequest request) {
        colaboradorService.salvar(request);
        return ResponseEntity.ok().build();
    }
}
