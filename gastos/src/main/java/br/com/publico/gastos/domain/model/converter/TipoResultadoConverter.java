package br.com.publico.gastos.domain.model.converter;

import br.com.publico.gastos.domain.model.TipoResultado;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

public class TipoResultadoConverter implements AttributeConverter<TipoResultado, String> {

    @Override
    public String convertToDatabaseColumn(TipoResultado tipoResultado) {
        return Objects.nonNull(tipoResultado) ? tipoResultado.getDescricao() : null;
    }

    @Override
    public TipoResultado convertToEntityAttribute(String tipoResultado) {
        return Arrays.stream(TipoResultado.values())
                .filter(resultado -> resultado.getDescricao().equalsIgnoreCase(tipoResultado))
                .findFirst().orElse(null);
    }
}
