package br.com.publico.gastos.services;

import java.time.LocalDate;

public interface ValidacoesService {

    Boolean verificaAvaliacaoExistente(String nome, String sigla, LocalDate data);

}
