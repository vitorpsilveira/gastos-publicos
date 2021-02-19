package br.com.publico.gastos.controller;

import br.com.publico.gastos.domain.dto.response.GastosCovidResponse;
import br.com.publico.gastos.services.CovidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/despesas")
public class DespesasController {

    private final CovidService covidService;

    @GetMapping("/covid/{ano}/{mes}")
    public ResponseEntity<List<GastosCovidResponse>> getDespesasCovid(@PathVariable String ano, @PathVariable String mes,
                                                                      @RequestParam(required = false) Integer pagina) {

        return ResponseEntity.ok(covidService.listGastosCovid((ano + mes), pagina));
    }

}