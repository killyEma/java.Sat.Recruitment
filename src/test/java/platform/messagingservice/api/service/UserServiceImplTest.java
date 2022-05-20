package platform.messagingservice.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.delegate.UserDecorator;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.domain.UserType;
import sat.recruitment.api.repository.UserRepository;
import sat.recruitment.api.service.UserServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private User user;
    private Exception exception;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDecorator userDecorator;
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Ema");
        user.setAddress("Street 1234");
        user.setMoney(100.0);
        user.setUserType(UserType.Normal);
        user.setPhone("6546");
        user.setEmail("ema@gmail.com");
        userService = new UserServiceImpl(userRepository, userDecorator);

        exception = new Exception();
    }

    @Test
    public void shouldCallAddGiftToMoneyUserMethod(){
        userService.saveUser(user);
        verify(userDecorator).addGiftToMoneyUser(user);
    }

    @Test
    public void shouldCallCreateMethod(){
        when(userDecorator.addGiftToMoneyUser(user)).thenReturn(user);
        userService.saveUser(user);

        verify(userRepository).create(user);
    }



    @Test
    public void shouldThrowBadRequestUserDuplicatedWhenUserAlreadyExist(){
        user.setName("Juan");
        user.setAddress("Peru 2464");
        when(userRepository.get(user)).thenReturn(Optional.of(user));

        exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            userService.saveUser(user);
        });

        String expectedMessage = "400 BAD_REQUEST \"User is duplicated\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

}
