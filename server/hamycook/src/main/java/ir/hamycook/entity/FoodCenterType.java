package ir.hamycook.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class FoodCenterType {
    @Id
    private String name;

    @OneToMany(mappedBy = "foodCenterType")
    private List<FoodCenter> foodCenters;

    public FoodCenterType(String name) {
        this.name = name;
    }
}
