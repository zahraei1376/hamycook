package ir.hamycook.repository;

import ch.qos.logback.core.util.TimeUtil;
import ir.hamycook.entity.OtpCode;
import ir.hamycook.entity.User;
import lombok.NonNull;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Period;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OtpCodeRepositoryTest {

    public static final String CODE = "213548";
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OtpCodeRepository otpCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save_saveOtpCode() {
        Timestamp timestamp =
                new Timestamp(DateUtils.addMinutes(new Date(System.currentTimeMillis()), 15).getTime());
        User user = new User("09105795896", "1278", "احسان مداحی");
        testEntityManager.persist(user);
        OtpCode otpCode = new OtpCode(CODE, timestamp, user);
        otpCodeRepository.save(otpCode);

        assertEquals(userRepository.findAll().size(), 1);
        Optional<OtpCode> optionalOtpCode = otpCodeRepository.findById(otpCode.getId());
        assertTrue(optionalOtpCode.isPresent());
        assertEquals(userRepository.findById(user.getPhone()).get(), optionalOtpCode.get().getUser());
        assertEquals(optionalOtpCode.get().getCode(), CODE);
    }

    @Test
    public void findByUserId() {
        Timestamp timestamp =
                new Timestamp(DateUtils.addMinutes(new Date(System.currentTimeMillis()), 15).getTime());
        User user = new User("09105795896", "1278", "احسان مداحی");
        testEntityManager.persist(user);
        OtpCode otpCode = new OtpCode(CODE, timestamp, user);
        testEntityManager.persist(otpCode);

        Optional<OtpCode> otpCodeOptional = otpCodeRepository.findByUserId(user.getPhone());
        assertTrue(otpCodeOptional.isPresent());
        assertEquals(otpCodeOptional.get().getCode(), CODE);
    }

    @Test
    public void deleteByUserId() {
        Timestamp timestamp =
                new Timestamp(DateUtils.addMinutes(new Date(System.currentTimeMillis()), 15).getTime());
        User user = new User("09105795896", "1278", "احسان مداحی");
        testEntityManager.persist(user);
        OtpCode otpCode = new OtpCode(CODE, timestamp, user);
        testEntityManager.persist(otpCode);

        otpCodeRepository.deleteByUserId(user.getPhone());

        OtpCode otpCodeAfterDelete = testEntityManager.find(OtpCode.class, otpCode.getId());
        assertNull(otpCodeAfterDelete);
    }

}