package sat.recruitment.api.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sat.recruitment.api.delegate.RulesSameUser;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.domain.UserType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImpTest {

    private UserRepository userRepository;
    private User user;
    private List<User> users;
    @Mock
    private RulesSameUser rules;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Ema");
        user.setAddress("Street 1234");
        user.setMoney(100.0);
        user.setUserType(UserType.Normal);
        user.setPhone("6546");
        user.setEmail("ema@gmail.com");

        users = new ArrayList<>();
        users.add(user);

        userRepository = new UserRepositoryImp(rules);
    }

    @Test
    public void verifyRulesWasCalled(){

//      TODO:  powermock
//        when(rules.runRulesUserRepeated(user, eq(anyList()))).thenReturn(Optional.empty());
//        userRepository.get(user);
//        verify(rules, times(1)).runRulesUserRepeated(user, eq(anyList()));
    }

}
