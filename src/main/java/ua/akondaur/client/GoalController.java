package ua.akondaur.client;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.akondaur.db.Goal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
class GoalController {
	@Autowired
	private GoalServiceClient goalServiceClient;

	@GetMapping("/goals")
	public Map getAllGoals() {
		return goalServiceClient.getAllGoals();
	}

	@GetMapping("/goals/{id}")
	public Map getGoalById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
		return goalServiceClient.getGoalById(id);
	}

	@PostMapping("/goals")
	public Map createGoal(@Valid @RequestBody Goal goal) {
		return goalServiceClient.createGoal(goal);
	}

	@PutMapping("/goals/{id}")
	public Map updateGoal(@PathVariable(value = "id") Long id, @Valid @RequestBody Goal goalDetails)
			throws ResourceNotFoundException {

		return goalServiceClient.updateGoal(id, goalDetails);
	}

	@DeleteMapping("/goals/{id}")
	public Map deleteGoal(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {

		return goalServiceClient.deleteGoal(id);
	}

}
