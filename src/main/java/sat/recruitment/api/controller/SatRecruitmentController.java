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
	public ResponseEntity createUser(@RequestBody User newUser) {
		validateErrors(newUser);
		validateUserDuplicated(newUser);


		newUser = addGiftToMoneyUserDecorator(newUser);
		userRepository.create(newUser);

		return ResponseEntity.created(null).build();
	}

	private void validateUserDuplicated(User newUser) {
		if(userRepository.get(newUser) != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is duplicated");
		}
	}

	public User addGiftToMoneyUserDecorator(User newUser) {
		Double money = newUser.getMoney();
		double gift = 0.0d;
		switch (newUser.getUserType()){
			case Premium: if (money > 100) gift = money * 2; break;
			case SuperUser: if (money > 100) gift = money * 0.2; break;
			case Normal: if (money > 100) gift = money * 0.12;
				 		 if (money < 100 && money > 10) gift = money * 0.8; break;
		}
		newUser.setMoney(money + gift);
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
