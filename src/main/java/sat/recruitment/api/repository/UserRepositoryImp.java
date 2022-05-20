package sat.recruitment.api.repository;

import org.springframework.stereotype.Service;
import sat.recruitment.api.delegate.Rules;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.domain.UserType;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRepositoryImp implements UserRepository {
    private Rules rules;

    public UserRepositoryImp(Rules rules){
        this.rules =rules;
    }

    @Override
    public Optional<User> get(User user) {

        List<User> users = new ArrayList<>();
        try (InputStream stream = getClass().getResourceAsStream("/users.txt")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {

                String strLine;

                while ((strLine = br.readLine()) != null) {
                    String[] line = strLine.split(",");
                    User userP = new User();
                    userP.setName(line[0]);
                    userP.setEmail(line[1]);
                    userP.setPhone(line[2]);
                    userP.setAddress(line[3]);
                    userP.setUserType(UserType.valueOf(line[4]));
                    userP.setMoney(Double.valueOf(line[5]));
                    users.add(userP);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rules.runRulesUserRepeated(user, users);
    }

    @Override
    public void create(User newUser) {
    //todo
    }
}
