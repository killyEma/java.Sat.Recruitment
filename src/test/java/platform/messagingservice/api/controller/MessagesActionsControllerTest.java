package platform.messagingservice.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.controller.SatRecruitmentController;
import sat.recruitment.api.controller.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MessagesActionsControllerTest {
    @Autowired
    private SatRecruitmentController controller;
    private User user;
    private Exception exception;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Ema");
        user.setAddress("calle 1234");
        user.setMoney(100.0);
        user.setUserType("suertope");
        user.setPhone("6546");
        user.setEmail("ema@gmail.com");
        controller = new SatRecruitmentController();

        exception = new Exception();
    }
    @Test
    public void testCreateUSer() {

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
}
