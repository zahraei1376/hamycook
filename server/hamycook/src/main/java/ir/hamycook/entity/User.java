package ir.hamycook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @Column(unique = true, length = 11)
    @NotBlank(message = "phone number is required")
    @Size(min = 10, max = 10)
    private String phone;

    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "fullName is required")
    private String fullName;
    @Pattern(regexp = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}", message = "please provide valid email address")
    private String email;
    @JsonIgnore
    private boolean enable = false;
    @JsonIgnore
    private boolean emailEnable = false;

    @OneToMany(mappedBy = "owner")
    private List<FoodCenter> foodCenters;

    public User(String phone, String password, String fullName) {
        this.phone = phone;
        this.password = password;
        this.fullName = fullName;
    }
}
