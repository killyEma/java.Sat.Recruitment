package sat.recruitment.api.delegate.rulesuser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.domain.UserType;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserHasSameEmailOrPhoneNumberRuleTest {

    private Rule rule;
    private User user;
    private List<User> users;

    @BeforeEach
    public void setup(){
        user = new User();
        user.setName("Ema");
        user.setAddress("Street 1234");
        user.setMoney(100.0);
        user.setUserType(UserType.Normal);
        user.setPhone("6546");
        user.setEmail("ema@gmail.com");

        users = new ArrayList<>();
        users.add(user);
        rule = new UserHasSameEmailOrPhoneNumberRule();
    }

    @Test
    public void shouldReturnUserWhenUserHasSameEmailThanAnother(){
        User userNew = new User();
        userNew.setEmail(user.getEmail());

        assertThat(rule.run(user, users)).isNotNull();
    }

    @Test
    public void shouldReturnUserWhenUserHasSamePhoneThanAnother(){
        User userNew = new User();
        userNew.setPhone(user.getPhone());

        assertThat(rule.run(user, users)).isNotNull();
    }

    @Test
    public void shouldReturnOptionalWhenUserDoesNotExist(){
        User userNew = new User();
        userNew.setEmail("Juan@marmol.com");
        userNew.setPhone("+5491154762312");

        assertThat(rule.run(userNew, users)).isEmpty();
    }


}