package cz.martlin.rainbowtodolist.model.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The task, one item of the rainbow todolist.
 * 
 * @author martin.jasek
 *
 */
public class RainbowTask {

	/** The ID of the task. */
	private final UUID id;

	/** The actual text of the task. */
	private final String text;

	/** The current status of the task. */
	private final RainbowTaskStatus status;

	/** The date and time of creation. */
	private final LocalDateTime created;

	/**
	 * Creates NEW task with the given text.
	 * 
	 * @param text
	 */
	public RainbowTask(String text) {
		super();
		this.id = UUID.randomUUID();
		this.text = text;
		this.status = RainbowTaskStatus.OPEN;
		this.created = LocalDateTime.now();
	}

	/**
	 * Creates task based on the existing. Do not call directly.
	 * 
	 * @param id
	 * @param text
	 * @param status
	 * @param created
	 */
	private RainbowTask(UUID id, String text, RainbowTaskStatus status, LocalDateTime created) {
		super();
		this.id = id;
		this.text = text;
		this.status = status;
		this.created = created;
	}

	/**
	 * Returns the id.
	 * 
	 * @return
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Returns the text.
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * Returns the current status.
	 * 
	 * @return
	 */
	public RainbowTaskStatus getStatus() {
		return status;
	}

	/**
	 * Returns the date of creation.
	 * 
	 * @return
	 */
	public LocalDateTime getCreated() {
		return created;
	}

	@Override
	public String toString() {
		return "RainbowTask [id=" + id + ", text=" + text + ", status=" + status + ", created=" + created + "]";
	}

	/**
	 * Returns new task as a copy of the given one, but with the specified text.
	 * 
	 * @param task
	 * @param newText
	 * @return
	 */
	public static RainbowTask withText(RainbowTask task, String newText) {
		return new RainbowTask(task.id, newText, task.status, task.created);
	}

	/**
	 * Returns new task as a copy of the given one, but with the specified status.
	 * 
	 * @param task
	 * @param anotherText
	 * @return
	 */
	public static RainbowTask withStatus(RainbowTask task, RainbowTaskStatus newStatus) {
		return new RainbowTask(task.id, task.text, newStatus, task.created);
	}

}
