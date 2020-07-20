package ir.hamycook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @Column(unique = true, length = 11)
    @NotEmpty(message = "phone number is required")
    @Size(min = 10, max = 10)
    private String phone;

    @NotEmpty(message = "password is required")
    private String password;
    @NotEmpty(message = "fullName is required")
    private String fullName;
    @Pattern(regexp = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}", message = "please provide valid email address")
    private String email;
    @JsonIgnore
    private boolean enable = false;

    public User(String phone, String password, String fullName) {
        this.phone = phone;
        this.password = password;
        this.fullName = fullName;
    }
}
