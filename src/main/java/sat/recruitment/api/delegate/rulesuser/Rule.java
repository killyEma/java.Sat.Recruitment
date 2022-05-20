package sat.recruitment.api.delegate.rulesuser;

import sat.recruitment.api.domain.User;

import java.util.List;
import java.util.Optional;

public interface Rule {
    Optional<User> run(User user, List<User> users);
}
