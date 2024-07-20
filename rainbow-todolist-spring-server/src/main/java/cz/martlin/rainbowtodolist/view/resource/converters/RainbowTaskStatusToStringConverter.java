package cz.martlin.rainbowtodolist.view.resource.converters;

import org.springframework.core.convert.converter.Converter;

import cz.martlin.rainbowtodolist.model.model.RainbowTaskStatus;

/**
 * Converter of the {@link RainbowTaskStatus} to string. Just picks the status
 * name, and converts that to lower-case string.
 * 
 * @see StringToRainbowTaskStatusConverter
 */
public class RainbowTaskStatusToStringConverter implements Converter<RainbowTaskStatus, String> {
	@Override
	public String convert(RainbowTaskStatus source) {
		return source.name().toLowerCase();
	}
}
