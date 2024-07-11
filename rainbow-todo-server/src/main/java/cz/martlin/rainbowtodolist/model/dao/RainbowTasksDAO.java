package cz.martlin.rainbowtodolist.model.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import cz.martlin.rainbowtodolist.model.dao.misc.RainbowTasksOrder;
import cz.martlin.rainbowtodolist.model.model.RainbowTask;

/**
 * The DAO for the {@link RainbowTask}s.
 * 
 * @author martin.jasek
 *
 */
public interface RainbowTasksDAO {

	/**
	 * Create new {@link RainbowTask}.
	 * 
	 * @param task
	 * @return
	 */
	RainbowTask create(RainbowTask task);

	/**
	 * Saves changes in the given {@link RainbowTask}.
	 * 
	 * @param task
	 * @return
	 */
	RainbowTask update(RainbowTask task);

	/**
	 * Removes the the given {@link RainbowTask}.
	 * 
	 * @param task
	 * @return
	 */
	RainbowTask delete(RainbowTask task);

	/**
	 * Lists all the {@link RainbowTask}s, possibly ordering them.
	 * 
	 * @param order
	 * 
	 * @return
	 */
	List<RainbowTask> list(Optional<RainbowTasksOrder> order);

	/**
	 * Find the {@link RainbowTask} by the given ID. Returns null if no such.
	 * 
	 * @return
	 */
	RainbowTask find(UUID id);

}
