package ir.hamycook.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class FoodType {

    @Id
    private String name;

    @OneToMany(mappedBy = "foodType")
    private List<Food> foods;

}
