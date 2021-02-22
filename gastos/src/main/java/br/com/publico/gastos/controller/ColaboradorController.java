package br.com.publico.gastos.controller;

import br.com.publico.gastos.domain.model.Colaborador;
import br.com.publico.gastos.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("colaborador")
public class ColaboradorController {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @GetMapping
    public List<Colaborador> getAll() {
        return colaboradorRepository.findAll();
    }

    @PostMapping
    public void save(@RequestBody Colaborador colaborador) {
        colaboradorRepository.save(colaborador);
    }
}
