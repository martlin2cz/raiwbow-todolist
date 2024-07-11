package cz.martlin.rainbowtodolist.model.dao.misc;

import java.util.Comparator;

import cz.martlin.rainbowtodolist.model.model.RainbowTask;

/**
 * The ordering specifier.
 */
public enum RainbowTasksOrder {
	/**
	 * Order the tasks by the text.
	 */
	BY_TEXT,

	/**
	 * Order the tasks by the date of created.
	 */
	BY_CREATION,

	/**
	 * Order the tasks by the current status.
	 */
	BY_STATUS;

	/**
	 * Returns the adequate comparator.
	 * 
	 * @return
	 */
	public Comparator<RainbowTask> getComparator() {
		return switch (this) {
		case BY_TEXT -> Comparator.comparing(RainbowTask::getText);
		case BY_CREATION -> Comparator.comparing(RainbowTask::getCreated);
		case BY_STATUS -> Comparator.comparing(RainbowTask::getStatus);
		default -> throw new IllegalArgumentException("Unexpected value: " + this);
		};
	}
}
