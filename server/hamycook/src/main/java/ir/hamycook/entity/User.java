package ir.hamycook.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(unique = true, length = 11)
    //@NotBlank(message = "phone number is required")
    //@Size(min = 10, max = 10)
    @NonNull
    private String phone;

    @NotBlank(message = "password is required")
    @Column(nullable = false)
    @NonNull
    private String password;

    //@NotBlank(message = "fullName is required")
    @Column(nullable = false)
    @NonNull
    private String fullName;

    //@Pattern(regexp = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}", message = "please provide valid email address")
    private String email;

    //@JsonIgnore
    private boolean enable = false;

    //@JsonIgnore
    private boolean emailEnable = false;

    @OneToMany(mappedBy = "owner")
    private List<FoodCenter> foodCenters;

}
