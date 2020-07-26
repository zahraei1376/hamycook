package ir.hamycook.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class FoodType {

    @Id
    private String name;

}
