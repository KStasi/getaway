package ua.akondaur.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Goal {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	public long id;

	public String topic;

	public String author;

	public String[] tags;

	public Long time;

	public String description;

	public String[] resources;

	public String[] reasons;

	public String measures;

	public String achievements;

	public Goal() {

	}

	public Goal(String topic, String author, String[] tags, Long time, String description, String[] resources,
			String[] reasons, String measures, String achievements) {
		this.topic = topic;
		this.author = author;
		this.tags = tags;
		this.time = time;
		this.description = description;
		this.resources = resources;
		this.reasons = reasons;
		this.measures = measures;
		this.achievements = achievements;
	}

	@Override
	public String toString() {
		return String.format(
				"Goal[id=%d, topic='%s', author='%s', tags='%s', time='%l', description='%s', resources='%s', reasons='%s', measures='%s', achievements='%s']",
				id, topic, author, String.join(" ", tags), time, description, String.join(" ", resources),
				String.join(" ", reasons), measures, achievements);
	}
}
