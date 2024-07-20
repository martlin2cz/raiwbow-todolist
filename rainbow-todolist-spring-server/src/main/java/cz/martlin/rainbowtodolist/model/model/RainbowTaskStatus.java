package cz.martlin.rainbowtodolist.model.model;

/**
 * The status of the rainbow todolist task.
 * 
 * @author martin.jasek
 */
public enum RainbowTaskStatus {
	/**
	 * The taks is open, waiting to be executed.
	 */
	OPEN,

	/**
	 * The task is currently in the progress.
	 */
	IN_PROGRESS,

	/**
	 * The task was completed succesfully.
	 */
	DONE,

	/**
	 * The task was canceled, terminated at any stage.
	 */
	CANCELED;
}
