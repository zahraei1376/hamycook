package ir.hamycook.service;

import ir.hamycook.config.PasswordEncoderConfig;
import ir.hamycook.entity.User;
import ir.hamycook.exception.UsernameAlreadyExistException;
import ir.hamycook.exception.UsernameNotFoundException;
import ir.hamycook.model.UserRegisterIn;
import ir.hamycook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final KavenegarSmsService kavenegarSmsService;

    public void registerUser(UserRegisterIn userRegisterIn) {
        if (userRepository.findById(userRegisterIn.getPhone()).isPresent()) {
            throw new UsernameAlreadyExistException(userRegisterIn.getPhone() + " already exist");
        }
        String encodedPassword = passwordEncoderConfig
                .passwordEncoder()
                .encode(userRegisterIn.getPassword());

        User user = new User(userRegisterIn.getPhone(),
                encodedPassword,
                userRegisterIn.getFullName());
        userRepository.save(user);
    }

    public void generateOtpCodeAndSend(String phone) {
        User currentUser = userRepository
                .findById(phone)
                .orElseThrow(() -> new UsernameNotFoundException(phone + " not exist"));
        String otpCode = generateOtp();
        kavenegarSmsService.sendOptCode(phone, otpCode);
    }

    public String generateOtp() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}

