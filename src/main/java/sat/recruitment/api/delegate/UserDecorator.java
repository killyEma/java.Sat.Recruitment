package sat.recruitment.api.delegate;

import org.springframework.stereotype.Component;
import sat.recruitment.api.domain.User;

@Component
public class UserDecorator {

    private static final int AVERAGE_MONEY = 100;
    private static final double PREMIUM_PERCENTAGE_GIFT = 2.0d;
    private static final double SUPER_USER_PERCENTAGE_GIFT = 0.2d;
    private static final double NORMAL_FIRST_PERCENTAGE_GIFT = 0.12d;
    private static final double NORMAL_SECOND_PERCENTAGE_GIFT = 0.8d;


    public User addGiftToMoneyUser(User newUser) {
        Double money = newUser.getMoney();
        double percentage = 0.0d;

        switch (newUser.getUserType()){
            case Premium: if (money > AVERAGE_MONEY) percentage = PREMIUM_PERCENTAGE_GIFT; break;
            case SuperUser: if (money > AVERAGE_MONEY) percentage = SUPER_USER_PERCENTAGE_GIFT; break;
            case Normal: if (money > AVERAGE_MONEY) percentage = NORMAL_FIRST_PERCENTAGE_GIFT;
                        if (money < AVERAGE_MONEY && money > 10) percentage = NORMAL_SECOND_PERCENTAGE_GIFT; break;
        }

        double giftToAdd = money * percentage;
        newUser.setMoney(money + giftToAdd);
        return newUser;
    }
}
