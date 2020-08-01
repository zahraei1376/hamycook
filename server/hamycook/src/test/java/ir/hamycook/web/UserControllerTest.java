package ir.hamycook.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.hamycook.dao.UserRegisterIn;
import ir.hamycook.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    }

    @Test
    public void registerUser_nullPhone() throws Exception {
        UserRegisterIn userRegisterIn =
                new UserRegisterIn(null, PASSWORD, FULL_NAME);

        mockMvc.perform(post("/users/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRegisterIn))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void registerUser_nullPassword() throws Exception {
        UserRegisterIn userRegisterIn =
                new UserRegisterIn(PHONE, null, FULL_NAME);

        mockMvc.perform(post("/users/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRegisterIn))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void registerUser_nullFullName() throws Exception {
        UserRegisterIn userRegisterIn =
                new UserRegisterIn(PHONE, PASSWORD, null);

        mockMvc.perform(post("/users/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRegisterIn))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void registerUser_spaceOnlyFullName() throws Exception {
        UserRegisterIn userRegisterIn =
                new UserRegisterIn(PHONE, PASSWORD, "  ");

        mockMvc.perform(post("/users/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRegisterIn))
        ).andExpect(status().isBadRequest());
    }

}