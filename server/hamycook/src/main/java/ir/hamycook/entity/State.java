package ir.hamycook.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class State {

    @Id
    @NotBlank
    @NonNull
    @EqualsAndHashCode.Include
    private String name;


}
