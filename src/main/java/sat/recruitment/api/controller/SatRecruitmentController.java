package sat.recruitment.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.repository.UserRepository;

@RestController
@RequestMapping(value = "/api/v1")
public class SatRecruitmentController {
	private UserRepository userRepository;

	public SatRecruitmentController (UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	//TODO: ver url
	@PostMapping(value = "user/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity createUser(@RequestBody User newUser) {
		validateErrors(newUser);
		validateUserDuplicated(newUser);


		newUser = addGiftToMoneyUserDecorator(newUser);
		userRepository.create(newUser);

		return ResponseEntity.ok().build();
	}

	private void validateUserDuplicated(User newUser) {
		if(userRepository.get(newUser) != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is duplicated");
		}
	}

	public User addGiftToMoneyUserDecorator(User newUser) {
		if (newUser.getUserType().equals("Normal")) {
			if (Double.valueOf(newUser.getMoney()) > 100) {
				Double percentage = Double.valueOf("0.12");
				// If new user is normal and has more than USD100
				var gif = Double.valueOf(newUser.getMoney()) * percentage;
				newUser.setMoney(newUser.getMoney() + gif);
			}
			if (Double.valueOf(newUser.getMoney()) < 100) {
				if (Double.valueOf(newUser.getMoney()) > 10) {
					var percentage = Double.valueOf("0.8");
					var gif = Double.valueOf(newUser.getMoney()) * percentage;
					newUser.setMoney(newUser.getMoney() + gif);
				}
			}
		}
		if (newUser.getUserType().equals("SuperUser")) {
			if (Double.valueOf(newUser.getMoney()) > 100) {
				Double percentage = Double.valueOf("0.20");
				Double gif = Double.valueOf(newUser.getMoney()) * percentage;
				newUser.setMoney(newUser.getMoney() + gif);
			}
		}
		if (newUser.getUserType().equals("Premium")) {
			if (Double.valueOf(newUser.getMoney()) > 100) {
				Double gif = Double.valueOf(newUser.getMoney()) * 2;
				newUser.setMoney(newUser.getMoney() + gif);
			}
		}
		return newUser;
	}

	private void validateErrors(User user) {
		String errors = "";
		if (user.getName() == null)
			// Validate if Name is null
			errors = "The name is required";
		if (user.getEmail() == null)
			// Validate if Email is null
			errors = errors + " The email is required";
		if (user.getAddress() == null)
			// Validate if Address is null
			errors = errors + " The address is required";
		if (user.getPhone() == null)
			// Validate if Phone is null
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
