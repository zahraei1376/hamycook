package ir.hamycook.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FoodCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "name is required")
    @NonNull
    private String name;

    @NotBlank(message = "address is required")
    @NonNull
    private String address;

    @NotBlank(message = "name is required")
    @Size(min = 11)
    @NonNull
    private String phone;

    @NotNull
    @NonNull
    private Time openTime;

    @NotNull
    @NonNull
    private Time closeTime;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @NotNull
    @NonNull
    private City city;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @NotNull
    @NonNull
    private User owner;

    @ManyToMany
    @JoinColumn(name = "food_center_type_id")
    @NotEmpty
    private List<FoodCenterType> foodCenterType = new ArrayList<>();


    public void addFoodCenterType(FoodCenterType foodCenterType) {
        this.foodCenterType.add(foodCenterType);
    }

    public void removeFoodCenterType(FoodCenterType foodCenterType) {
        this.foodCenterType.remove(foodCenterType);
    }

}
