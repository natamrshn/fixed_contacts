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
@Table(name = "lenses_cylinder")
public class Cylinder implements RangeProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String minCylinder;
    private String maxCylinder;
    private String cylinderStep;

    @Override
    public List<Double> getRangeAsList() {
        return createRange(minCylinder, maxCylinder, cylinderStep);
    }
}
