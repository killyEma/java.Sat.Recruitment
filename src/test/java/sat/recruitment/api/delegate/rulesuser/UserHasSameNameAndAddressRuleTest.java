package sat.recruitment.api.delegate.rulesuser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.domain.UserType;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserHasSameNameAndAddressRuleTest {
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
        rule = new UserHasSameNameAndAddressRule();
    }

    @Test
    public void shouldReturnUserWhenUserNameAndAddressAlreadyExist(){
        User userNew = new User();
        userNew.setName(user.getName());
        userNew.setAddress(user.getAddress());

        assertThat(rule.run(user, users)).isNotNull();
    }

    @Test
    public void shouldReturnNoUserWhenUserHasSameAddressButNotSameName(){
        User userNew = new User();
        userNew.setName("Name new");
        userNew.setAddress(user.getAddress());

        assertThat(rule.run(userNew, users)).isEmpty();
    }

    @Test
    public void shouldReturnNoUserWhenUserHasSameNameButNotSameAddress(){
        User userNew = new User();
        userNew.setName(user.getName());
        userNew.setAddress("other 1234");

        assertThat(rule.run(userNew, users)).isEmpty();
    }

    @Test
    public void shouldReturnNoUserWhenUserHasNotSameAddressAndName(){
        User userNew = new User();
        userNew.setName("Name new");
        userNew.setAddress("other 1234");

        assertThat(rule.run(userNew, users)).isEmpty();
    }

}