package ir.hamycook.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.List;

@Entity
@Data
public class FoodCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "name is required")
    @Size(min = 10)
    private String phone;

    private Time openTime;
    private Time closeTime;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "food_center_type_id")
    private FoodCenterType foodCenterType;

    @OneToMany(mappedBy = "foodCenter")
    private List<Food> foods;

    public FoodCenter(@NotBlank(message = "name is required") String name,
                      @NotBlank(message = "address is required") String address,
                      @NotBlank(message = "name is required") @Size(min = 10) String phone,
                      Time openTime,
                      Time closeTime,
                      City city) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.city = city;
    }
}
