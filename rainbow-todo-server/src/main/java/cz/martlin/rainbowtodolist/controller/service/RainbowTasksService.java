package cz.martlin.rainbowtodolist.controller.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.martlin.rainbowtodolist.model.dao.RainbowTasksDAO;
import cz.martlin.rainbowtodolist.model.dao.misc.RainbowTasksOrder;
import cz.martlin.rainbowtodolist.model.model.RainbowTask;
import cz.martlin.rainbowtodolist.model.model.RainbowTaskStatus;

/**
 * The service for the {@link RainbowTask}s manipulation.
 * 
 * @author martin.jasek
 *
 */
@Service
public class RainbowTasksService {

	/**
	 * The DAO for the persistence of the {@link RainbowTask}s.
	 */
	private final RainbowTasksDAO dao;

	/**
	 * Creates.
	 * 
	 * @param dao
	 */
	@Autowired
	public RainbowTasksService( //
			RainbowTasksDAO dao) {

		super();
		this.dao = dao;
		
		RainbowTask task = createNew("Try the app");
		System.out.println(task);
	}

	/**
	 * Creates new task. Returns the new task.
	 * 
	 * @param text
	 * @return
	 */
	public RainbowTask createNew(String text) {
		RainbowTask task = new RainbowTask(text);
		dao.create(task);
		return task;
	}

	/**
	 * Updates the text of the given task. Returns the updated task.
	 * 
	 * @param task
	 * @param newText
	 * @return
	 */
	public RainbowTask updateText(RainbowTask task, String newText) {
		RainbowTask newTask = RainbowTask.withText(task, newText);
		dao.update(newTask);
		return newTask;
	}

	/**
	 * Changes the status of the given task. Returns the updated task.
	 * 
	 * @param task
	 * @param newStatus
	 * @return
	 */
	public RainbowTask changeStatus(RainbowTask task, RainbowTaskStatus newStatus) {
		RainbowTask newTask = RainbowTask.withStatus(task, newStatus);
		dao.update(newTask);
		return newTask;
	}

	/**
	 * Removes the given task.
	 * 
	 * @param task
	 * @return
	 */
	public RainbowTask remove(RainbowTask task) {
		dao.delete(task);
		return task;
	}

	/**
	 * Lists all the tasks, ordered by the given order.
	 * 
	 * @return
	 */
	public List<RainbowTask> list(Optional<RainbowTasksOrder> order) {
		return dao.list(order);
	}

	/**
	 * Lists all the tasks, unordered.
	 * 
	 * @return
	 */
	public List<RainbowTask> list() {
		return dao.list(Optional.empty());
	}

	/**
	 * Returns the task with the given id.
	 * 
	 * @param id
	 * @return
	 */
	public RainbowTask get(UUID id) {
		return dao.find(id);
	}
}
