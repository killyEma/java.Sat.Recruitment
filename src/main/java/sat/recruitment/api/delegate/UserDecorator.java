package sat.recruitment.api.delegate;

import org.springframework.stereotype.Service;
import sat.recruitment.api.domain.User;

@Service
public class UserDecorator {
    public User addGiftToMoneyUser(User newUser) {
        Double money = newUser.getMoney();
        double gift = 0.0d;
        switch (newUser.getUserType()){
            case Premium: if (money > 100) gift = money * 2; break;
            case SuperUser: if (money > 100) gift = money * 0.2; break;
            case Normal: if (money > 100) gift = money * 0.12;
                if (money < 100 && money > 10) gift = money * 0.8; break;
        }
        newUser.setMoney(money + gift);
        return newUser;
    }
}
