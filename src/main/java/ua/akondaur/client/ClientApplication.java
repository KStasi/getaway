package ua.akondaur.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.akondaur.db.GoalRepository;
import ua.akondaur.db.SequenceGeneratorService;
import ua.akondaur.db.Goal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@EnableDiscoveryClient
@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

}

@RestController
@RequestMapping("/api")
class GoalController {
	@Autowired
	private GoalRepository goalRepository;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@GetMapping("/goals")
	public List<Goal> getAllGoals() {
		return goalRepository.findAll();
	}

	@GetMapping("/goals/{id}")
	public ResponseEntity<Goal> getGoalById(@PathVariable(value = "id") long id) {
		return ResponseEntity.ok().body(goalRepository.findById(id));
	}

	@PostMapping("/goals")
	public Goal createGoal(@Valid @RequestBody Goal goal) {
		goal.id = sequenceGeneratorService.generateSequence(Goal.SEQUENCE_NAME);
		return goalRepository.save(goal);
	}

	@PutMapping("/goals/{id}")
	public ResponseEntity<Goal> updateGoal(@PathVariable(value = "id") Long id, @Valid @RequestBody Goal goalDetails) {
		Goal goal = goalRepository.findById(id);

		goal.topic = goalDetails.topic;
		goal.author = goalDetails.author;
		goal.tags = goalDetails.tags;
		goal.time = goalDetails.time;
		goal.description = goalDetails.description;
		goal.resources = goalDetails.resources;
		goal.reasons = goalDetails.reasons;
		goal.measures = goalDetails.measures;
		goal.achievements = goalDetails.achievements;
		final Goal updatedGoal = goalRepository.save(goal);
		return ResponseEntity.ok(updatedGoal);
	}

	@DeleteMapping("/goals/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id) {
		Goal goal = goalRepository.findById(id);

		goalRepository.delete(goal);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
