package platform.messagingservice.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.controller.SatRecruitmentController;
import sat.recruitment.api.delegate.UserDecorator;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.domain.UserType;
import sat.recruitment.api.repository.UserRepository;
import sat.recruitment.api.repository.UserRepositoryImp;
import sat.recruitment.api.service.UserService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MessagesActionsControllerTest {
    //@Autowired
    private SatRecruitmentController controller;
    private User user;
    private Exception exception;
    private UserRepository userRepository;
    private UserService userService;
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
        controller = new SatRecruitmentController(userRepository, userService);

        exception = new Exception();
    }

    @Test
    public void shouldThrowBadRequestWhenUserNameIsNull(){
        user.setName(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            controller.createUser(user);
        });

        String expectedMessage = "400 BAD_REQUEST \"The name is required\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenUserEmailIsNull(){
        user.setEmail(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            controller.createUser(user);
        });

        String expectedMessage = "400 BAD_REQUEST \" The email is required\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenUserAddressIsNull(){
        user.setAddress(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            controller.createUser(user);
        });

        String expectedMessage = "400 BAD_REQUEST \" The address is required\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenUserPhoneIsNull(){
        user.setPhone(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            controller.createUser(user);
        });

        String expectedMessage = "400 BAD_REQUEST \" The phone is required\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
    @Test
    public void shouldThrowBadRequestWhenUserTypeIsNull(){
        user.setUserType(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            controller.createUser(user);
        });

        String expectedMessage = "400 BAD_REQUEST \" The userType is required\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenMoneyIsNull(){
        user.setMoney(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            controller.createUser(user);
        });

        String expectedMessage = "400 BAD_REQUEST \" The Money is required\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }


    @Test
    public void shouldReturn201CreatedWhenUserWasCreated(){
        assertThat(controller.createUser(user)
                             .getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
