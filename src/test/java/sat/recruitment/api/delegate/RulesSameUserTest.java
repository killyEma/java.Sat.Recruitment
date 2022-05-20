package sat.recruitment.api.delegate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.domain.UserType;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class RulesSameUserTest {


    private RulesSameUser ruleSameUser;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Ema");
        user.setAddress("Street 1234");
        user.setMoney(100.0);
        user.setUserType(UserType.Normal);
        user.setPhone("6546");
        user.setEmail("ema@gmail.com");
        ruleSameUser = new RulesSameUser();

    }

    @Test
    public void shouldReturnUser(){
        var userOptional = ruleSameUser.runRulesUserRepeated(user, List.of(user));
       assertThat(userOptional).isNotNull();
    }

    @Test
    public void shouldReturnDoesNotUserWhenUserNameAndAddressAlreadyExist(){
        var ruleSameUser = new RulesSameUser();

        var userOptional = ruleSameUser.runRulesUserRepeated(user, List.of(user));
        assertThat(userOptional).isNotNull();
    }
}