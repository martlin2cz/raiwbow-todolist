package cz.martlin.rainbowtodolist.view.resource.converters;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;

import cz.martlin.rainbowtodolist.model.model.RainbowTaskStatus;

/**
 * The coverter, which converts the message of a type text/plain to {@link RainbowTaskStatus} and back.
 * 
 */
public class RainbowTaskStatusMessageConverter implements HttpMessageConverter<RainbowTaskStatus> {

	/**
	 * The converter of string TO http message and back.
	 */
	private final StringHttpMessageConverter innerStringConverter;
	/**
	 * The converter of task status TO string.
	 */
	private final RainbowTaskStatusToStringConverter statusToStringConverter;
	/**
	 * The converter of string TO task status.
	 */
	private final StringToRainbowTaskStatusConverter stringToStatusConverter;

	/**
	 * Creates.
	 * 
	 * @param innerStringConverter
	 * @param statusToStringConverter
	 * @param stringToStatusConverter
	 */
	@Autowired
	public RainbowTaskStatusMessageConverter(StringHttpMessageConverter innerStringConverter,
			RainbowTaskStatusToStringConverter statusToStringConverter,
			StringToRainbowTaskStatusConverter stringToStatusConverter) {
		super();
		this.innerStringConverter = innerStringConverter;
		this.statusToStringConverter = statusToStringConverter;
		this.stringToStatusConverter = stringToStatusConverter;
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return RainbowTaskStatus.class.equals(clazz) && MediaType.TEXT_PLAIN.equalsTypeAndSubtype(mediaType);
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return RainbowTaskStatus.class.equals(clazz) && MediaType.TEXT_PLAIN.equalsTypeAndSubtype(mediaType);
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return List.of(MediaType.TEXT_PLAIN);
	}

	@Override
	public RainbowTaskStatus read(Class<? extends RainbowTaskStatus> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		String bodyStr = innerStringConverter.read(String.class, inputMessage);
		return stringToStatusConverter.convert(bodyStr);
	}

	@Override
	public void write(RainbowTaskStatus t, MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		String bodyStr = statusToStringConverter.convert(t);
		innerStringConverter.write(bodyStr, contentType, outputMessage);
	}

}
