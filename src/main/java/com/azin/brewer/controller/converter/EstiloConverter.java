package com.azin.brewer.controller.converter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.azin.brewer.model.Estilo;

@Component
@ConfigurationPropertiesBinding
public class EstiloConverter implements Converter<String, Estilo> {

	@Override
	public Estilo convert(String source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		Estilo estilo = new Estilo();
		estilo.setCodigo(Long.valueOf(source));
		return estilo;
	}

}
