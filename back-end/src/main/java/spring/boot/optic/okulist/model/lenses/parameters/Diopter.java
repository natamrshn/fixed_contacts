package spring.boot.optic.okulist.model.lenses.parameters;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "lenses_diopter")
public class Diopter implements RangeProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String minDiopter;
    private String maxDiopter;
    private String step;

    @Override
    public List<Double> getRangeAsList() {
        return createRange(minDiopter, maxDiopter, step);
    }
}
