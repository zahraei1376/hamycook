package ir.hamycook.web;

import ir.hamycook.dao.UserRegisterIn;
import ir.hamycook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegisterIn userRegisterIn) {
        userService.registerUser(userRegisterIn);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
