package cz.martlin.rainbowtodolist.view.resource.converters;

import org.springframework.core.convert.converter.Converter;

import cz.martlin.rainbowtodolist.model.model.RainbowTaskStatus;

/**
 * The converter of string to {@link RainbowTaskStatus}. Expects the status
 * name, in lower case.
 * 
 * @see RainbowTaskStatusToStringConverter
 */
public class StringToRainbowTaskStatusConverter implements Converter<String, RainbowTaskStatus> {
	@Override
	public RainbowTaskStatus convert(String source) {
		return RainbowTaskStatus.valueOf(source.toUpperCase());
	}
}
