package cz.martlin.rainbowtodolist.view.resource;

import java.util.Objects;

/**
 * The response object of the {@link RainbowTasksResource}. Encapsulated the
 * actual {@link #result} value of the service, but adding extra informations:
 * simple explanation what happened, along with simple count of the tasks.
 * 
 * @param <T>
 */
public class RainbowTasksResponse<T> {

	/**
	 * The text explanation of the response, what data it contains or what action
	 * have been succesfully performed.
	 */
	private final String explanation;
	/**
	 * The actual response data object (or collection).
	 */
	private final T result;
	/**
	 * The number of tasks responded.
	 */
	private final int tasks;

	/**
	 * Creates.
	 * 
	 * @param explanation
	 * @param result
	 * @param tasks
	 */
	public RainbowTasksResponse(String explanation, T result, int tasks) {
		super();
		this.explanation = Objects.requireNonNull(explanation);
		this.result = result;
		this.tasks = tasks;
	}

	/**
	 * Returns the explanation.
	 * 
	 * @return
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * Returns the items.
	 * 
	 * @return
	 */
	public int getItems() {
		return tasks;
	}

	/**
	 * Returns the number of tasks.
	 * 
	 * @return
	 */
	public T getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "RaibowTasksResponse [explanation=" + explanation + ", result=" + result + ", tasks=" + tasks + "]";
	}
}
