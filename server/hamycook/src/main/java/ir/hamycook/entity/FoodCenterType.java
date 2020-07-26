package ir.hamycook.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class FoodCenterType {

    @Id
    @EqualsAndHashCode.Include
    private String name;

    @ManyToMany(mappedBy = "foodCenterType")
    private List<FoodCenter> foodCenters = new ArrayList<>();

    public FoodCenterType(String name) {
        this.name = name;
    }

    public void addFoodCenter(FoodCenter foodCenter) {
        this.foodCenters.add(foodCenter);
    }

    public void removeFoodCenter(FoodCenter foodCenter) {
        this.foodCenters.remove(foodCenter);
    }
}
