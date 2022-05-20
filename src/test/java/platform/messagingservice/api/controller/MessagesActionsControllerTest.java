package platform.messagingservice.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.controller.SatRecruitmentController;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.domain.UserType;
import sat.recruitment.api.repository.UserRepository;
import sat.recruitment.api.service.UserService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessagesActionsControllerTest {

    private SatRecruitmentController controller;
    private User user;
    private Exception exception;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Ema");
        user.setAddress("Street 1234");
        user.setMoney(100.0);
        user.setUserType(UserType.Normal);
        user.setPhone("6546");
        user.setEmail("ema@gmail.com");
        controller = new SatRecruitmentController(userRepository, userService);

        exception = new Exception();
    }

    @Test
    public void shouldThrowBadRequestWhenUserNameIsNull(){
        user.setName(null);
        exception = assertThrows(ResponseStatusException.class,
                                () -> controller.createUser(user));

        String expectedMessage = "400 BAD_REQUEST \"The name is required\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenUserEmailIsNull(){
        user.setEmail(null);
        exception = assertThrows(ResponseStatusException.class,
                                () -> controller.createUser(user));

        String expectedMessage = "400 BAD_REQUEST \" The email is required\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenUserAddressIsNull(){
        user.setAddress(null);
        exception = assertThrows(ResponseStatusException.class,
                                () -> controller.createUser(user));

        String expectedMessage = "400 BAD_REQUEST \" The address is required\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenUserPhoneIsNull(){
        user.setPhone(null);
        exception = assertThrows(ResponseStatusException.class,
                                () -> controller.createUser(user));

        String expectedMessage = "400 BAD_REQUEST \" The phone is required\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
    @Test
    public void shouldThrowBadRequestWhenUserTypeIsNull(){
        user.setUserType(null);
        exception = assertThrows(ResponseStatusException.class,
                                () -> controller.createUser(user));

        String expectedMessage = "400 BAD_REQUEST \" The userType is required\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenMoneyIsNull(){
        user.setMoney(null);
        exception = assertThrows(ResponseStatusException.class,
                                () -> controller.createUser(user));

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
