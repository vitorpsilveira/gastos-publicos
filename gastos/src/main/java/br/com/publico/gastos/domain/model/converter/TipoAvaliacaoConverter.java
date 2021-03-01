package br.com.publico.gastos.domain.model.converter;

import br.com.publico.gastos.domain.model.TipoAvaliacao;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

public class TipoAvaliacaoConverter implements AttributeConverter<TipoAvaliacao, String> {

    @Override
    public String convertToDatabaseColumn(TipoAvaliacao tipoAvaliacao) {
        return Objects.nonNull(tipoAvaliacao) ? tipoAvaliacao.getDescricao() : null;
    }

    @Override
    public TipoAvaliacao convertToEntityAttribute(String tipoAvaliacao) {
        return Arrays.stream(TipoAvaliacao.values())
                .filter(avaliacao -> avaliacao.getDescricao().equalsIgnoreCase(tipoAvaliacao))
                .findFirst().orElse(null);
    }
}
