package ir.hamycook.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class City {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    //@NotBlank
    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    @NonNull
    private State state;


}
