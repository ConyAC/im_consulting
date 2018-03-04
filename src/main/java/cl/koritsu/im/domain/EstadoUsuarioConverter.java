package cl.koritsu.im.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import cl.koritsu.im.domain.enums.EstadoUsuario;

@Converter(autoApply=true)
public class EstadoUsuarioConverter implements AttributeConverter<EstadoUsuario, Integer> {

	public Integer convertToDatabaseColumn(EstadoUsuario arg0) {
		return arg0.getCorrelative();
	}

	public EstadoUsuario convertToEntityAttribute(Integer arg0) {
		return EstadoUsuario.getUserStatus(arg0);
	}
}
