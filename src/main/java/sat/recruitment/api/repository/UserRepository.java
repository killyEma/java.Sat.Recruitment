package sat.recruitment.api.repository;

import sat.recruitment.api.domain.User;

public interface UserRepository {
    User get(User user);

    void create(User newUser);
}
