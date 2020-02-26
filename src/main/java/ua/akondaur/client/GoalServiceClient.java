package ua.akondaur.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.akondaur.db.GoalRepository;
import ua.akondaur.db.SequenceGeneratorService;
import ua.akondaur.client.ResourceNotFoundException;
import ua.akondaur.db.Goal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("client")
public interface GoalServiceClient {

	@GetMapping("/goals")
	public Map getAllGoals();

	@GetMapping("/goals/{id}")
	public Map getGoalById(@PathVariable(value = "id") long id);

	@PostMapping("/goals")
	public Map createGoal(@Valid @RequestBody Goal goal);

	@PutMapping("/goals/{id}")
	public Map updateGoal(@PathVariable(value = "id") Long id, @Valid @RequestBody Goal goalDetails);

	@DeleteMapping("/goals/{id}")
	public Map deleteGoal(@PathVariable(value = "id") Long id);

}
