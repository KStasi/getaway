package ua.akondaur.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.akondaur.db.Goal;

public interface GoalRepository extends MongoRepository<Goal, Long> {
	// public Goal findById(long id);

	public Goal findByTopic(String topic);

	public Goal findByAuthor(String author);

	public Goal findByTags(String[] tags);

	public Goal findByResources(String[] resources);

	public Goal findByReasons(String[] reasons);
}