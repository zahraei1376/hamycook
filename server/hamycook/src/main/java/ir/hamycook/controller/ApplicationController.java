package ir.hamycook.controller;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ir.hamycook.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("test")
@Log4j2
public class ApplicationController {

    @GetMapping()
    public String test() {
        return "Hello World";
    }

    @PostMapping("user")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Validation Error")
            }
    )
    public ResponseEntity<User> userTest( @ApiParam(name = "user", value = "user information for sign up") @Valid @RequestBody User user) {
        log.info("user" + user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
