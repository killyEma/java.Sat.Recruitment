package platform.messagingservice.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.delegate.UserDecorator;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.domain.UserType;
import sat.recruitment.api.repository.UserRepository;
import sat.recruitment.api.repository.UserRepositoryImp;
import sat.recruitment.api.service.UserService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserServiceTest {

    private User user;
    private Exception exception;
    private UserRepository userRepository;
    private UserService userService;
    private UserService service;
    private UserDecorator userDecorator;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Ema");
        user.setAddress("calle 1234");
        user.setMoney(100.0);
        user.setUserType(UserType.Normal);
        user.setPhone("6546");
        user.setEmail("ema@gmail.com");
        userRepository = new UserRepositoryImp();
        userDecorator = new UserDecorator();
        userService = new UserService(userRepository, userDecorator);

        exception = new Exception();

    }

    @Test
    public void shouldThrowBadRequestUserDuplicatedWhenUserAlreadyExist(){
        user.setName("Juan");
        user.setAddress("Peru 2464");

        exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            userService.saveUser(user);
        });

        String expectedMessage = "400 BAD_REQUEST \"User is duplicated\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    //Todo llamar al decorador y llamar al repo


}
