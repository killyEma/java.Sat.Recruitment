package sat.recruitment.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.repository.UserRepository;
import sat.recruitment.api.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/recruitment/api/v1")
public class SatRecruitmentController {
	private UserRepository userRepository;
	private UserService userService;

	public SatRecruitmentController (UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}

	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createUser(@Valid @RequestBody User newUser) {
		userService.saveUser(newUser);

		return ResponseEntity.noContent().build();
	}

}
