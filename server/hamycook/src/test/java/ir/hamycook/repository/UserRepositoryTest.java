package ir.hamycook.repository;

import ir.hamycook.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;
    public static final String PHONE = "09105788798";
    public static final String PASSWORD = "ehsan123";
    public static final String FULL_NAME = "سيد احسان مداحي";

    @Test
    public void findAll_shouldReturnNullWhenRepositoryIsEmpty() {
        List<User> users = userRepository.findAll();
        assertThat(users).isEmpty();
    }

    @Test
    public void save_storeUser() {
        // save user
        User userMustBeSave = new User(PHONE, PASSWORD, FULL_NAME);
        User user = userRepository.save(userMustBeSave);

        // retrieve user with id
        Optional<User> savedUser = userRepository.findById(PHONE);

        assertThat(user).hasFieldOrPropertyWithValue("phone", PHONE);
        assertThat(user).hasFieldOrPropertyWithValue("password", PASSWORD);
        assertThat(user).hasFieldOrPropertyWithValue("fullName", FULL_NAME);
        assertThat(user).hasFieldOrPropertyWithValue("enable", false);
        assertThat(user).hasFieldOrPropertyWithValue("emailEnable", false);

        assertTrue(savedUser.isPresent());
    }

    @Test(expected = Exception.class)
    public void save_nullPhoneField() {
        User user = new User(null, PASSWORD, FULL_NAME);
        userRepository.save(user);

    }

    @Test(expected = Exception.class)
    public void save_nullPassword() {
        User user = new User(PHONE, null, FULL_NAME);
        userRepository.save(user);

    }

    @Test(expected = Exception.class)
    public void save_nullFullName() {
        User user = new User(PHONE, PASSWORD, null);
        userRepository.save(user);
    }

    @Test
    public void save_updateUser() {
        User user = new User(PHONE, PASSWORD, FULL_NAME);
        User user2 = new User(PHONE, PASSWORD + PASSWORD, FULL_NAME + FULL_NAME);
        user2.setEmailEnable(true);

        userRepository.save(user);
        User returnedUser2 = userRepository.save(user2);

        assertEquals(userRepository.findAll().size(), 1);

        assertThat(returnedUser2).hasFieldOrPropertyWithValue("password", PASSWORD + PASSWORD);
        assertThat(returnedUser2).hasFieldOrPropertyWithValue("fullName", FULL_NAME + FULL_NAME);
        assertThat(returnedUser2).hasFieldOrPropertyWithValue("enable", false);
        assertThat(returnedUser2).hasFieldOrPropertyWithValue("emailEnable", true);
    }

    @Test
    public void findById_getUserByPhone() {
        User user1 = new User(PHONE, PASSWORD, FULL_NAME);
        testEntityManager.persist(user1);

        User user2 = new User("09106374873", PASSWORD + PASSWORD, FULL_NAME);
        testEntityManager.persist(user2);

        User user = userRepository.findById(user1.getPhone()).get();

        assertNotNull(user);
        assertThat(user).isEqualTo(user1);
    }


    @Test
    public void deleteById_deleteUser() {
        User user1 = new User(PHONE, PASSWORD, FULL_NAME);
        testEntityManager.persist(user1);

        assertEquals(userRepository.findAll().size(), 1);
        userRepository.deleteById(user1.getPhone());

        assertEquals(userRepository.findAll().size(), 0);
    }

    @Test(expected = Exception.class)
    public void deleteById_phoneNotExist() {
        User user1 = new User(PHONE, PASSWORD, FULL_NAME);
        testEntityManager.persist(user1);

        userRepository.deleteById(PHONE+PHONE);
    }



}