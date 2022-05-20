package sat.recruitment.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.delegate.UserDecorator;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserDecorator userDecorator;

    public UserServiceImpl(UserRepository userRepository, UserDecorator userDecorator) {
        this.userRepository = userRepository;
        this.userDecorator = userDecorator;
    }


    public void saveUser(User newUser) {
        validateUserDuplicated(newUser);
        newUser = userDecorator.addGiftToMoneyUser(newUser);
        userRepository.create(newUser);
    }

    private void validateUserDuplicated(User newUser) {
        if(userRepository.get(newUser).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is duplicated");
        }
    }
}
