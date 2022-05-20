package platform.messagingservice.api.respository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sat.recruitment.api.domain.User;
import sat.recruitment.api.domain.UserType;
import sat.recruitment.api.repository.UserRepository;
import sat.recruitment.api.repository.UserRepositoryImp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserRepositoryImpTest {

    private UserRepository userRepository;
    private User user;

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
    }

    @Test
    public void shouldReturnUserWhenUserHasSameEmailThanAnother(){
        user.setEmail("Juan@marmol.com");
        assertThat(userRepository.get(user)).isNotNull();
    }

    @Test
    public void shouldReturnUserWhenUserHasSamePhoneThanAnother(){
        user.setPhone("+5491154762312");
        assertThat(userRepository.get(user)).isNotNull();
    }

    @Test
    public void shouldReturnUserWhenUserNameAndAddressAlreadyExist(){
        user.setName("Juan");
        user.setAddress("Peru 2464");

        assertThat(userRepository.get(user)).isNotNull();
    }

    @Test
    public void shouldReturnOptionalWhenUserDoesNotExist(){
        assertThat(userRepository.get(user)).isEmpty();
    }


    /*
    todo hacer estos test a nivel de api
    @Test
    public void shouldThrowBadRequestUserDuplicatedWhenUserHasSameEmailThanAnother(){
        user.setEmail("Juan@marmol.com");

        exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            controller.createUser(user);
        });

        String expectedMessage = "400 BAD_REQUEST \"User is duplicated\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestUserDuplicatedWhenUserHasSamePhoneThanAnother(){
        user.setPhone("+5491154762312");

        exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            controller.createUser(user);
        });

        String expectedMessage = "400 BAD_REQUEST \"User is duplicated\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
    @Test
    public void shouldThrowBadRequestUserDuplicatedWhenUserNameAndAddressAlreadyExist(){
        user.setName("Juan");
        user.setAddress("Peru 2464");

        exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            userService.saveUser(user);
        });

        String expectedMessage = "400 BAD_REQUEST \"User is duplicated\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

     */

}
