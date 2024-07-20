package cz.martlin.rainbowtodolist.model.daos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import cz.martlin.rainbowtodolist.model.dao.RainbowTasksDAO;
import cz.martlin.rainbowtodolist.model.dao.misc.RainbowTasksOrder;
import cz.martlin.rainbowtodolist.model.model.RainbowTask;

/**
 * The in-memory implementation of the {@link RainbowTask}s DAO.
 * 
 * @author martin.jasek
 *
 */
@Repository
public class RainbowTasksInMemoryDAO implements RainbowTasksDAO {

	/**
	 * The actual "database" table.
	 */
	private final Map<UUID, RainbowTask> tasks = new LinkedHashMap<>();

	@Override
	public RainbowTask create(RainbowTask task) {
		if (tasks.containsKey(task.getId())) {
			throw new IllegalArgumentException("Such task already exist!");
		}
		
		tasks.put(task.getId(), task);
		return task;
	}

	@Override
	public RainbowTask update(RainbowTask task) {
		tasks.put(task.getId(), task);
		return task;
	}

	@Override
	public RainbowTask delete(RainbowTask task) {
		tasks.remove(task.getId());
		return task;
	}

	@Override
	public List<RainbowTask> list(Optional<RainbowTasksOrder> order) {
		ArrayList<RainbowTask> list = new ArrayList<>(tasks.values());

		if (order.isEmpty()) {
			return list;
		} else {
			Comparator<RainbowTask> comparator = order.get().getComparator();
			Collections.sort(list, comparator);
			return list;
		}
	}

	@Override
	public RainbowTask find(UUID id) {
		return tasks.get(id);
	}

	@Override
	public String toString() {
		return "RainbowTasksInMemoryDAO [tasks=" + tasks.values() + "]";
	}

}
