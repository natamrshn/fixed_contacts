package spring.boot.optic.okulist.model.lenses.parameters;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@SQLDelete(sql = "Update manufacturers SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "manufacturers")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "manufacturers_colors",
            joinColumns = @JoinColumn(name = "manufacturer_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id"))
    private List<Color> colors;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cylinder_id")
    private Cylinder cylinder;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "degree_id")
    private Degree degree;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diopter_id")
    private Diopter diopter;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "manufacturers_spheres",
            joinColumns = @JoinColumn(name = "manufacturer_id"),
            inverseJoinColumns = @JoinColumn(name = "sphere_id"))
    private List<Sphere> spheres;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
