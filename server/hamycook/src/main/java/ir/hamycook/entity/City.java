package ir.hamycook.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    //@NotBlank
    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    @NonNull
    private State state;

    @OneToMany(mappedBy = "city")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<FoodCenter> foodCenters;

}
