package ir.hamycook.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.hamycook.model.ErrorResponse;
import ir.hamycook.model.UserRegisterIn;
import ir.hamycook.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    public static final String PHONE = "09105795986";
    public static final String PASSWORD = "ehsan123";
    public static final String FULL_NAME = "احسان مداحی";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void registerUser_validInput() throws Exception {
        UserRegisterIn userRegisterIn =
                new UserRegisterIn(PHONE, PASSWORD, FULL_NAME);

        mockMvc.perform(post("/users/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRegisterIn))
        ).andExpect(status().isOk());

        verify(userService, times(1)).registerUser(userRegisterIn);
    }

    @Test
    public void registerUser_nullPhone() throws Exception {
        UserRegisterIn userRegisterIn =
                new UserRegisterIn(null, PASSWORD, FULL_NAME);

        MvcResult mvcResult = mockMvc.perform(post("/users/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRegisterIn))
        ).andExpect(status().isBadRequest()).andReturn();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.addErrorMessage("phone", "phone number is required");

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(errorResponse)
        );

    }

    @Test
    public void registerUser_not11DigitPhone() throws Exception {
        UserRegisterIn userRegisterIn =
                new UserRegisterIn(PHONE + "1", PASSWORD, FULL_NAME);

        MvcResult mvcResult = mockMvc.perform(post("/users/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRegisterIn))
        ).andExpect(status().isBadRequest()).andReturn();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.addErrorMessage("phone", "phone number must be 11 digits");

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(errorResponse)
        );
    }

    @Test
    public void registerUser_nullPassword() throws Exception {
        UserRegisterIn userRegisterIn =
                new UserRegisterIn(PHONE, null, FULL_NAME);

        MvcResult mvcResult = mockMvc.perform(post("/users/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRegisterIn))
        ).andExpect(status().isBadRequest()).andReturn();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.addErrorMessage("password", "password is required");

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(errorResponse)
        );
    }

    @Test
    public void registerUser_nullFullName() throws Exception {
        UserRegisterIn userRegisterIn =
                new UserRegisterIn(PHONE, PASSWORD, null);

        MvcResult mvcResult = mockMvc.perform(post("/users/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRegisterIn))
        ).andExpect(status().isBadRequest()).andReturn();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.addErrorMessage("fullName", "fullName is required");

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(errorResponse)
        );
    }

    @Test
    public void registerUser_spaceOnlyFullName() throws Exception {
        UserRegisterIn userRegisterIn =
                new UserRegisterIn(PHONE, PASSWORD, "  ");

        MvcResult mvcResult = mockMvc.perform(post("/users/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRegisterIn))
        ).andExpect(status().isBadRequest()).andReturn();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.addErrorMessage("fullName", "fullName is required");

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(errorResponse)
        );
    }

}