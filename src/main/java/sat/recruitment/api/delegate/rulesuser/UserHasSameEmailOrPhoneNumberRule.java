package sat.recruitment.api.delegate.rulesuser;

import org.springframework.stereotype.Component;
import sat.recruitment.api.domain.User;

import java.util.List;
import java.util.Optional;

@Component
public class UserHasSameEmailOrPhoneNumberRule implements Rule{
    @Override
    public Optional<User> run(User user1, List<User> users) {
        return users.stream().filter(user -> user.getEmail().equals(user1.getEmail()) ||
                                             user.getPhone().equals(user1.getPhone()))
                             .findAny();
    }
}
