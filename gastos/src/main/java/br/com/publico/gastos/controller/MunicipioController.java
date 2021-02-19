package br.com.publico.gastos.controller;

import br.com.publico.gastos.domain.dto.response.ConsultaMunicipioResponse;
import br.com.publico.gastos.services.MunicipioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/municipios")
public class MunicipioController {

    private final MunicipioService municipioService;

    @GetMapping()
    public ResponseEntity<List<ConsultaMunicipioResponse>> getMunicipios() {

        return ResponseEntity.ok(municipioService.listaMunicipios());

    }
}
