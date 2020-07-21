package ir.hamycook.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "food_type")
    private FoodType foodType;

    private double price;
    private double reducedPrice;

    private Long remainFoodNumber;

    @ManyToOne
    @JoinColumn(name = "food_center")
    private FoodCenter foodCenter;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Purchase> purchases;
}
