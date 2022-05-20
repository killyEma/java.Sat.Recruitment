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

@RestController
@RequestMapping(value = "/api/v1/recruitment")
public class SatRecruitmentController {
	private UserRepository userRepository;
	private UserService userService;

	public SatRecruitmentController (UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}

	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createUser(@RequestBody User newUser) {
		validateErrors(newUser);

		userService.saveUser(newUser);

		return ResponseEntity.noContent().build();
	}

	private void validateErrors(User user) {
		String errors = "";
		if (user.getName() == null)
			errors = "The name is required";
		if (user.getEmail() == null)
			errors = errors + " The email is required";
		if (user.getAddress() == null)
			errors = errors + " The address is required";
		if (user.getPhone() == null)
			errors = errors + " The phone is required";
		if (user.getUserType() == null)
			errors = errors + " The userType is required";
		if (user.getMoney() == null)
			errors = errors + " The Money is required";

		if (!errors.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
		}
	}

}
