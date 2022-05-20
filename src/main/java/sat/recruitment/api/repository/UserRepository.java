package sat.recruitment.api.repository;

import sat.recruitment.api.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> get(User user);

    void create(User newUser);
}
