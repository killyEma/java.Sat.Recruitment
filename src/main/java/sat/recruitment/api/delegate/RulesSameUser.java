package sat.recruitment.api.delegate;

import org.springframework.stereotype.Component;
import sat.recruitment.api.delegate.rulesuser.Rule;
import sat.recruitment.api.delegate.rulesuser.UserHasSameEmailOrPhoneNumberRule;
import sat.recruitment.api.delegate.rulesuser.UserHasSameNameAndAddressRule;
import sat.recruitment.api.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class RulesSameUser implements Rules {
    final private List<Rule> rules = new ArrayList<>(Arrays.asList(new UserHasSameEmailOrPhoneNumberRule(),
                                                                    new UserHasSameNameAndAddressRule()));
    @Override
    public Optional<User> runRulesUserRepeated(User user, List<User> users) {
        for (Rule rule : rules) {
            Optional<User> userRepeated = rule.run(user,users);
            if (userRepeated.isPresent()) return userRepeated;
        }
        return Optional.empty();
    }

}
