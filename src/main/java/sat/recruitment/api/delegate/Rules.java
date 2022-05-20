package sat.recruitment.api.delegate;

import sat.recruitment.api.domain.User;

import java.util.List;
import java.util.Optional;

public interface Rules {

    Optional<User> runRulesUserRepeated(User user, List<User> users);
}
