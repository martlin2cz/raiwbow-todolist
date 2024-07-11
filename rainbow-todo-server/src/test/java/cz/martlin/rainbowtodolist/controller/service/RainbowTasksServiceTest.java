package cz.martlin.rainbowtodolist.controller.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cz.martlin.rainbowtodolist.model.dao.misc.RainbowTasksOrder;
import cz.martlin.rainbowtodolist.model.model.RainbowTask;
import cz.martlin.rainbowtodolist.model.model.RainbowTaskStatus;

/**
 * The test of the {@link RainbowTasksService}.
 */
@SpringBootTest
class RainbowTasksServiceTest {

	/**
	 * The service we are testing.
	 */
	private final RainbowTasksService service;

	/**
	 * The testing task we are manipulating with.
	 */
	private RainbowTask loremIpsumTask;

	/**
	 * Creates.
	 * 
	 * @param service
	 */
	@Autowired
	public RainbowTasksServiceTest(RainbowTasksService service) {
		this.service = service;
	}

	@BeforeEach
	public void createTestingTask() {
		loremIpsumTask = service.createNew("lorem");
	}

	@AfterEach
	public void removeTestingTask() {
		if (service.get(loremIpsumTask.getId()) != null) {
			service.remove(loremIpsumTask);
		}
		
		loremIpsumTask = null;
	}

	@Test
	void testCreateNew() {
		RainbowTask fooTask = service.createNew("foo");

		assertThat(fooTask.getText()).isEqualTo("foo");
		assertThat(fooTask.getStatus()).isEqualTo(RainbowTaskStatus.OPEN);
	}

	@Test
	void testGet() {
		UUID lipsumTaskID = loremIpsumTask.getId();

		RainbowTask gettedTask = service.get(lipsumTaskID);

		UUID gettedID = gettedTask.getId();
		assertEquals(lipsumTaskID, gettedID);

		assertThat(gettedTask.getText()).isEqualTo("lorem");
		assertThat(gettedTask.getStatus()).isEqualTo(RainbowTaskStatus.OPEN);
	}

	@Test
	void testList() {
		List<RainbowTask> listedUnordered = service.list();

		assertTrue(listedUnordered.stream().anyMatch(t -> t.getText() == "lorem"));

		List<RainbowTask> listedOrdered = service.list(Optional.of(RainbowTasksOrder.BY_TEXT));

		assertTrue(listedOrdered.stream().anyMatch(t -> t.getText() == "lorem"));
	}

	@Test
	void testUpdateText() {
		UUID originalID = loremIpsumTask.getId();

		loremIpsumTask = service.updateText(loremIpsumTask, "ipsum");

		UUID newID = loremIpsumTask.getId();
		assertEquals(originalID, newID);

		assertThat(loremIpsumTask.getText()).isEqualTo("ipsum");
	}

	@Test
	void testChangeStatus() {
		UUID originalID = loremIpsumTask.getId();

		loremIpsumTask = service.changeStatus(loremIpsumTask, RainbowTaskStatus.CANCELED);

		UUID newID = loremIpsumTask.getId();
		assertEquals(originalID, newID);

		assertThat(loremIpsumTask.getStatus()).isEqualTo(RainbowTaskStatus.CANCELED);
	}

	@Test
	void testRemove() {
		UUID originalID = loremIpsumTask.getId();

		loremIpsumTask = service.remove(loremIpsumTask);

		RainbowTask removedLipsumTask = service.get(originalID);
		assertNull(removedLipsumTask);
	}

}
