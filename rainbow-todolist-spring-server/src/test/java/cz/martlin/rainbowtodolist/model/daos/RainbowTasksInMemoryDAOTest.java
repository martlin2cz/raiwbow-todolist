package cz.martlin.rainbowtodolist.model.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import cz.martlin.rainbowtodolist.model.dao.RainbowTasksDAO;
import cz.martlin.rainbowtodolist.model.dao.misc.RainbowTasksOrder;
import cz.martlin.rainbowtodolist.model.model.RainbowTask;
import cz.martlin.rainbowtodolist.model.model.RainbowTaskStatus;

/**
 * The test of the {@link RainbowTasksInMemoryDAO}.
 * 
 */
@TestMethodOrder(OrderAnnotation.class)
class RainbowTasksInMemoryDAOTest {

	private static final RainbowTasksDAO dao = new RainbowTasksInMemoryDAO();

	private static UUID beerTaskId;
	private static UUID wineTaskId;
	private static UUID vodkaTaskId;

	@Order(1)
	@Test
	void testCreate() {
		System.out.println(dao);
		
		RainbowTask beerTask = new RainbowTask("Drink a beer");
		dao.create(beerTask);
		beerTaskId = beerTask.getId();
		System.out.println(dao);

		RainbowTask wineTask = new RainbowTask("Drink a wine");
		dao.create(wineTask);
		wineTaskId = wineTask.getId();
		System.out.println(dao);

		RainbowTask vodkaTask = new RainbowTask("Drink a vodka");
		dao.create(vodkaTask);
		vodkaTaskId = vodkaTask.getId();
		System.out.println(dao);
	}

	@Order(2)
	@Test
	void testList() {
		System.out.println(dao);
		
		List<RainbowTask> tasks = dao.list(Optional.of(RainbowTasksOrder.BY_CREATION));
		RainbowTask beerTask = tasks.get(0);
		assertEquals(beerTaskId, beerTask.getId());
		assertEquals("Drink a beer", beerTask.getText());
		assertEquals(RainbowTaskStatus.OPEN, beerTask.getStatus());

		RainbowTask wineTask = tasks.get(1);
		assertEquals(wineTaskId, wineTask.getId());
		assertEquals("Drink a wine", wineTask.getText());
		assertEquals(RainbowTaskStatus.OPEN, wineTask.getStatus());

		RainbowTask vodkaTask = tasks.get(2);
		assertEquals(vodkaTaskId, vodkaTask.getId());
		assertEquals("Drink a vodka", vodkaTask.getText());
		assertEquals(RainbowTaskStatus.OPEN, vodkaTask.getStatus());
	}
	

	@Order(3)
	@Test
	void testUpdate() {
		RainbowTask beerTask = dao.find(beerTaskId);
		RainbowTask BeerTaskInProgress = RainbowTask.withStatus(beerTask, RainbowTaskStatus.IN_PROGRESS);
		RainbowTask updatedBeerTask = dao.update(BeerTaskInProgress);
		System.out.println(dao);
		
		assertEquals(beerTaskId, updatedBeerTask.getId());
		assertEquals("Drink a beer", updatedBeerTask.getText());
		assertEquals(RainbowTaskStatus.IN_PROGRESS, updatedBeerTask.getStatus());
		
		
		RainbowTask vodkaTask = dao.find(vodkaTaskId);
		RainbowTask rumTask = RainbowTask.withText(vodkaTask, "Drink rum");
		dao.update(rumTask);
		System.out.println(dao);
		
		assertEquals(vodkaTaskId, rumTask.getId());
		assertEquals("Drink rum", rumTask.getText());
		assertEquals(RainbowTaskStatus.OPEN, rumTask.getStatus());
	}
		

	@Order(4)
	@Test
	void testDelete() {
		RainbowTask wineTask = dao.find(wineTaskId);
		dao.delete(wineTask);
		System.out.println(dao);
	}


	@Order(5)
	@Test
	void testFind() {
		System.out.println(dao);

		RainbowTask beerTask = dao.find(beerTaskId);
		assertEquals(beerTaskId, beerTask.getId());
		assertEquals("Drink a beer", beerTask.getText());
		assertEquals(RainbowTaskStatus.IN_PROGRESS, beerTask.getStatus());

		RainbowTask wineTask = dao.find(wineTaskId);
		assertNull(wineTask);

		RainbowTask rumTask = dao.find(vodkaTaskId);
		assertEquals(vodkaTaskId, rumTask.getId());
		assertEquals("Drink rum", rumTask.getText());
		assertEquals(RainbowTaskStatus.OPEN, rumTask.getStatus());
	}

}
