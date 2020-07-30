package ir.hamycook.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "food_type")
    @NonNull
    private FoodType foodType;

    @Min(0)
    @NotNull
    @NonNull
    private double actualPrice;

    @Min(0)
    private double discount;

    @Min(0)
    @NotNull
    @NonNull
    private Long remainFoodNumber;

    @ManyToOne
    @JoinColumn(name = "food_center")
    @NonNull
    private FoodCenter foodCenter;

}
