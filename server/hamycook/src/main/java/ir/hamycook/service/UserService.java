package ir.hamycook.service;

import ir.hamycook.config.PasswordEncoderConfig;
import ir.hamycook.dao.UserRegisterIn;
import ir.hamycook.entity.User;
import ir.hamycook.exception.UsernameAlreadyExistException;
import ir.hamycook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;

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
}
