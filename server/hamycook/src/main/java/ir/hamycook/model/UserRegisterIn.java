package ir.hamycook.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserRegisterIn {

    @NotBlank(message = "phone number is required")
    @Size(min = 11, max = 11, message = "phone number must be 11 digits")
    private String phone;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "fullName is required")
    private String fullName;
}
