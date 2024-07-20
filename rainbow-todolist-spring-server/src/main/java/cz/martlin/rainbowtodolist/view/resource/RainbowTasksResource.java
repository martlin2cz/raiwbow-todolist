package cz.martlin.rainbowtodolist.view.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.martlin.rainbowtodolist.controller.service.RainbowTasksService;
import cz.martlin.rainbowtodolist.model.dao.misc.RainbowTasksOrder;
import cz.martlin.rainbowtodolist.model.model.RainbowTask;
import cz.martlin.rainbowtodolist.model.model.RainbowTaskStatus;

/**
 * The web endpoint service resource for the {@link RainbowTask}.
 * 
 * @author martin.jasek
 *
 */
@RestController
@RequestMapping("api/v0.1/rainbowtodolist/tasks")
public class RainbowTasksResource {

	/**
	 * The service.
	 */
	private final RainbowTasksService service;

	/**
	 * Creates.
	 * 
	 * @param service
	 */
	@Autowired
	public RainbowTasksResource(RainbowTasksService service) {
		super();

		this.service = service;
	}

	/**
	 * Lists (all) the tasks, in the optional order.
	 *  
	 * @param order
	 * @return
	 */
	@GetMapping( //
			produces = MediaType.APPLICATION_JSON_VALUE) //
	public RainbowTasksResponse<List<RainbowTask>> list(@RequestParam Optional<RainbowTasksOrder> order) {
		List<RainbowTask> listed = service.list(order);
		return response(listed, "All tasks");
	}

	/**
	 * Adds new task.
	 * 
	 * @param text
	 * @return
	 */
	@PutMapping( //
			consumes = MediaType.TEXT_PLAIN_VALUE, //
			produces = MediaType.APPLICATION_JSON_VALUE) //
	public RainbowTasksResponse<RainbowTask> add(@RequestBody String text) {
		RainbowTask created = service.createNew(text);
		return response(created, "Created task");
	}

	/**
	 * Returns task with the given id.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping( //
			path = "/{id}", //
			produces = MediaType.APPLICATION_JSON_VALUE) //
	public RainbowTasksResponse<RainbowTask> get(@PathVariable UUID id) {
		RainbowTask got = service.get(id);
		return response(got, "Task with id");
	}

	/**
	 * Updates text of the specified task.
	 * 
	 * @param id
	 * @param newText
	 * @return
	 */
	@PatchMapping( //
			path = "/{id}/text", //
			consumes = MediaType.TEXT_PLAIN_VALUE, //
			produces = MediaType.APPLICATION_JSON_VALUE) //
	public RainbowTasksResponse<RainbowTask> updateText(@PathVariable UUID id, @RequestBody String newText) {
		RainbowTask task = service.get(id);
		RainbowTask updated = service.updateText(task, newText);
		return response(updated, "Updated task text");
	}

	/**
	 * Changes the status of the specified task.
	 * 
	 * @param id
	 * @param newStatus
	 * @return
	 */
	@PatchMapping( //
			path = "/{id}/status", //
			consumes = MediaType.TEXT_PLAIN_VALUE, //
			produces = MediaType.APPLICATION_JSON_VALUE) //
	public RainbowTasksResponse<RainbowTask> changeStatus(@PathVariable UUID id,
			@RequestBody RainbowTaskStatus newStatus) {
		RainbowTask task = service.get(id);
		RainbowTask updated = service.changeStatus(task, newStatus);
		return response(updated, "Changed task status");
	}

	/**
	 * Removes the given task.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping( //
			path = "/{id}", //
			produces = MediaType.APPLICATION_JSON_VALUE) //
	public RainbowTasksResponse<RainbowTask> remove(@PathVariable UUID id) {
		RainbowTask task = service.get(id);
		RainbowTask removed = service.remove(task);
		return response(removed, "Removed task");
	}

	/**
	 * Constructs the response object of the given response data.
	 * 
	 * @param <T>
	 * @param result
	 * @param explanation
	 * @return
	 */
	private <T> RainbowTasksResponse<T> response(T result, String explanation) {
		int count = service.list().size();
		return new RainbowTasksResponse<>(explanation, result, count);
	}

}
