package sat.recruitment.api.repository;

import org.springframework.stereotype.Service;
import sat.recruitment.api.domain.User;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepositoryImp implements UserRepository {

    @Override
    public User get(User user) {

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
                    userP.setUserType(line[4]);
                    user.setMoney(Double.valueOf(line[5]));
                    users.add(userP);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (User userp : users) {
            if (userp.getEmail().equals(user.getEmail()) ||
                    userp.getPhone().equals(user.getPhone())) {
                return userp;
            } else if (userp.getName().equals(user.getName()) &&
                    userp.getAddress().equals(user.getAddress())) {
                return userp;
            }
        }

        return null;
    }

    @Override
    public void create(User newUser) {

    }
}
