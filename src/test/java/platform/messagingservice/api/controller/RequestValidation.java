package platform.messagingservice.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import sat.recruitment.api.SatRecruitmentApplication;
import sat.recruitment.api.domain.User;
import sat.recruitment.api.domain.UserType;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SatRecruitmentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RequestValidation {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Ema");
        user.setAddress("Street 1234");
        user.setMoney(100.0);
        user.setUserType(UserType.Normal);
        user.setPhone("6546");
        user.setEmail("ema@gmail.com");
    }

    @Test
    public void shouldThrowBadRequestWhenUserNameIsNull() throws Exception{
        user.setName(null);
        String body = objectMapper.writeValueAsString(user);
        String expectedMessage = "400 BAD_REQUEST \"The name is required\"";

        String error = Objects.requireNonNull
                (mvc.perform(post("/recruitment/api/v1/user")
                                .contentType("application/json")
                                .content(body))
                        .andExpect(status().isBadRequest())
                        .andReturn()
                        .getResolvedException()).getMessage();



        assertThat(error).contains(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenUserEmailIsNull()  throws Exception{
        user.setEmail(null);
        String body = objectMapper.writeValueAsString(user);
        String expectedMessage = "400 BAD_REQUEST \" The email is required\"";

        String error = Objects.requireNonNull
                (mvc.perform(post("/recruitment/api/v1/user")
                                .contentType("application/json")
                                .content(body))
                        .andExpect(status().isBadRequest())
                        .andReturn()
                        .getResolvedException()).getMessage();


        assertThat(error).contains(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenUserAddressIsNull()  throws Exception{
        user.setAddress(null);
        String body = objectMapper.writeValueAsString(user);
        String error = Objects.requireNonNull
                        (mvc.perform(post("/recruitment/api/v1/user")
                                .contentType("application/json")
                                .content(body))
                        .andExpect(status().isBadRequest())
                        .andReturn()
                        .getResolvedException()).getMessage();

        String expectedMessage = "400 BAD_REQUEST \" The address is required\"";

        assertThat(error).contains(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenUserPhoneIsNull()  throws Exception{
        user.setPhone(null);
        String body = objectMapper.writeValueAsString(user);
        String error = Objects.requireNonNull
                (mvc.perform(post("/recruitment/api/v1/user")
                                .contentType("application/json")
                                .content(body))
                        .andExpect(status().isBadRequest())
                        .andReturn()
                        .getResolvedException()).getMessage();

        String expectedMessage = "400 BAD_REQUEST \" The phone is required\"";

        assertThat(error).contains(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenUserTypeIsNull() throws Exception{
        user.setUserType(null);
        String body = objectMapper.writeValueAsString(user);
        String error = Objects.requireNonNull
                (mvc.perform(post("/recruitment/api/v1/user")
                                .contentType("application/json")
                                .content(body))
                        .andExpect(status().isBadRequest())
                        .andReturn()
                        .getResolvedException()).getMessage();

        String expectedMessage = "400 BAD_REQUEST \" The userType is required\"";

        assertThat(error).contains(expectedMessage);
    }

    @Test
    public void shouldThrowBadRequestWhenMoneyIsNull() throws Exception{
        user.setMoney(null);
        String body = objectMapper.writeValueAsString(user);
        String error = Objects.requireNonNull
                (mvc.perform(post("/recruitment/api/v1/user")
                                .contentType("application/json")
                                .content(body))
                        .andExpect(status().isBadRequest())
                        .andReturn()
                        .getResolvedException()).getMessage();

        String expectedMessage = "400 BAD_REQUEST \" The Money is required\"";

        assertThat(error).contains(expectedMessage);
    }

}
