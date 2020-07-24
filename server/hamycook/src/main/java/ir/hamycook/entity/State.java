package ir.hamycook.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class State {
    @Id
    //@NotBlank
    @NonNull
    @EqualsAndHashCode.Include
    private String name;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<City> cities = new ArrayList<>();


}
