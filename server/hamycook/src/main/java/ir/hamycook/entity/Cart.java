package ir.hamycook.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    @NonNull
    private User user;


    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    @NotNull
    @NonNull
    private Food food;

    @Min(1)
    @NotNull
    @NonNull
    private Long count;

}
