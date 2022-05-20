package sat.recruitment.api.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sat.recruitment.api.delegate.UserDecorator;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.domain.UserType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserDecoratorTest {

    private User user;
    private UserDecorator userDecorator;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Ema");
        user.setAddress("Street 1234");
        user.setMoney(100.0);
        user.setUserType(UserType.Normal);
        user.setPhone("6546");
        user.setEmail("ema@gmail.com");
        userDecorator = new UserDecorator();
    }

    @Test
    public void shouldAdd12PercentageWhenUserTypeIsNormalAndMoneyIsGreaterThan100(){
        user.setUserType(UserType.Normal);
        user.setMoney(200.0);

        Double MoneyWithGift = userDecorator.addGiftToMoneyUser(user).getMoney();

        assertThat(MoneyWithGift).isEqualTo(224.0);
    }

    @Test
    public void shouldAdd80PercentageWhenUserTypeIsNormalAndMoneyIsBetween10And100(){
        user.setUserType(UserType.Normal);
        user.setMoney(50.0);

        Double MoneyWithGift = userDecorator.addGiftToMoneyUser(user).getMoney();

        assertThat(MoneyWithGift).isEqualTo(90.0);
    }

    @Test
    public void whenMoneyIs100AndUserTypeIsNormalTheGiftPercentageIs0(){
        user.setUserType(UserType.Normal);
        user.setMoney(100.0);

        Double MoneyWithGift = userDecorator.addGiftToMoneyUser(user).getMoney();

        assertThat(MoneyWithGift).isEqualTo(100.0);
    }

    @Test
    public void shouldAdd20PercentageWhenUserTypeIsSuperUserAndMoneyIsGreaterThan100(){
        user.setUserType(UserType.SuperUser);
        user.setMoney(200.0);

        Double MoneyWithGift = userDecorator.addGiftToMoneyUser(user).getMoney();

        assertThat(MoneyWithGift).isEqualTo(240.0);
    }

    @Test
    public void shouldAdd0PercentageWhenUserTypeIsSuperUserAndMoneyIsLessThan100(){
        user.setUserType(UserType.SuperUser);
        user.setMoney(100.0);

        Double MoneyWithGift = userDecorator.addGiftToMoneyUser(user).getMoney();

        assertThat(MoneyWithGift).isEqualTo(100.0);
    }
    @Test
    public void shouldAdd200PercentageWhenUserTypeIsPremiumAndMoneyIsGreaterThan100(){
        user.setUserType(UserType.Premium);
        user.setMoney(101.0);

        Double MoneyWithGift = userDecorator.addGiftToMoneyUser(user).getMoney();

        assertThat(MoneyWithGift).isEqualTo(303.0);
    }

    @Test
    public void shouldAdd0PercentageWhenUserTypeIsPremiumAndMoneyIsLessThan100(){
        user.setUserType(UserType.Premium);
        user.setMoney(99.0);

        Double MoneyWithGift = userDecorator.addGiftToMoneyUser(user).getMoney();

        assertThat(MoneyWithGift).isEqualTo(99.0);
    }
}
