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
    private List<FoodType> foodType = new ArrayList<>();

    @OneToMany(mappedBy = "foodCenter", cascade = CascadeType.ALL)
    private List<Food> food = new ArrayList<>();

    public void addFoodType(FoodType foodType) {
        this.foodType.add(foodType);
    }

    public void removeFoodType(FoodType foodType) {
        this.foodType.remove(foodType);
    }

    public void addFood(Food food) {
        this.food.add(food);
    }

    public void removeFood(Food food) {
        this.food.remove(food);
    }
}
