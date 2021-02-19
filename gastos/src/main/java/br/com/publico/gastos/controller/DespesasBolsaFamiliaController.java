package br.com.publico.gastos.controller;

import br.com.publico.gastos.domain.dto.response.GastosBolsaFamiliaResponse;
import br.com.publico.gastos.services.BolsaFamiliaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/despesas")
public class DespesasBolsaFamiliaController {

    private final BolsaFamiliaService bolsaFamiliaService;

    @GetMapping("/BolsaFamilia/{ano}/{mes}/{codigoIbge}")
    public ResponseEntity<List<GastosBolsaFamiliaResponse>> getDespesasBolsaFamilia(@PathVariable String ano, @PathVariable String mes, @PathVariable String codigoIbge) {

        return ResponseEntity.ok(bolsaFamiliaService.listBolsaFamilia((ano + mes), codigoIbge));
    }

}