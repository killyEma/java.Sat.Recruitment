package sat.recruitment.api.delegate.rulesuser;

import org.springframework.stereotype.Component;
import sat.recruitment.api.domain.User;

import java.util.List;
import java.util.Optional;

@Component
public class UserHasSameNameAndAddressRule implements Rule{
    @Override
    public Optional<User> run(User user, List<User> users) {
        return users.stream().filter(user1 -> user1.getName().equals(user.getName()) &&
                                              user1.getAddress().equals(user.getAddress()))
                             .findAny();
    }
}
