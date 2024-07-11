package cz.martlin.rainbowtodolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;

import cz.martlin.rainbowtodolist.view.resource.converters.RainbowTaskStatusMessageConverter;
import cz.martlin.rainbowtodolist.view.resource.converters.RainbowTaskStatusToStringConverter;
import cz.martlin.rainbowtodolist.view.resource.converters.StringToRainbowTaskStatusConverter;

/**
 * The main spring boot executable and configuration.
 */
@SpringBootApplication
public class RainbowTodolistApplication {

	/**
	 * Starts the server.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RainbowTodolistApplication.class, args);
	}

	/**
	 * Builds instance of {@link StringToRainbowTaskStatusConverter}.
	 * 
	 * @return
	 */
	@Bean
	public StringToRainbowTaskStatusConverter createStringToRainbowTaskStatusConverter() {
		return new StringToRainbowTaskStatusConverter();
	}

	/**
	 * Builds instance of {@link RainbowTaskStatusToStringConverter}.
	 * 
	 * @return
	 */
	@Bean
	public RainbowTaskStatusToStringConverter createRainbowTaskStatusToStringConverter() {
		return new RainbowTaskStatusToStringConverter();
	}

	/**
	 * Builds the {@link RainbowTaskStatusMessageConverter}.
	 * 
	 * @param innerStringConverter
	 * @param statusToStringConverter
	 * @param stringToStatusConverter
	 * @return
	 */
	@Bean
	@Autowired
	public RainbowTaskStatusMessageConverter createRainbowTaskStatusMessageConverter(
			StringHttpMessageConverter innerStringConverter, RainbowTaskStatusToStringConverter statusToStringConverter,
			StringToRainbowTaskStatusConverter stringToStatusConverter) {

		return new RainbowTaskStatusMessageConverter(innerStringConverter, statusToStringConverter,
				stringToStatusConverter);
	}

}
