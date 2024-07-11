package cz.martlin.rainbowtodolist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cz.martlin.rainbowtodolist.controller.service.RainbowTasksService;

/**
 * The elementary test of the {@link RainbowTasksService}, just to check the
 * application is running.
 */
@SpringBootTest
class RainbowTodolistApplicationTests {

	/**
	 * The {@link RainbowTasksService} instance to check.
	 */
	@Autowired
	private RainbowTasksService controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
