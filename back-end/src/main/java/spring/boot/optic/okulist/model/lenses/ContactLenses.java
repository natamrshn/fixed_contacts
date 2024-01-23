package spring.boot.optic.okulist.model.lenses;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import spring.boot.optic.okulist.model.Product;
import spring.boot.optic.okulist.model.lenses.parameters.Manufacturer;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@SQLDelete(sql = "Update products SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "contact_lenses")
public class ContactLenses extends Product {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturers_id")
    private Manufacturer lensConfiguration;
}
