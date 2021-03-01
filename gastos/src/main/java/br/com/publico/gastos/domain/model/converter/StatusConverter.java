package br.com.publico.gastos.domain.model.converter;

import br.com.publico.gastos.domain.model.Status;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        return Objects.nonNull(status) ? status.getDescricao() : null;
    }

    @Override
    public Status convertToEntityAttribute(String status) {
        return Arrays.stream(Status.values())
                .filter(s -> s.getDescricao().equalsIgnoreCase(status))
                .findFirst().orElse(null);
    }
}
