package ir.hamycook.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;
    private double totalPrice = 0;

    @OneToMany
    private List<Food> foods = new ArrayList<>();

    public void addFood(Food food, long numberOfFood, double basePrice) {
        totalPrice += (numberOfFood * basePrice);
        foods.add(food);
    }

}
