package spring.boot.optic.okulist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import spring.boot.optic.okulist.model.user.User;

@Entity
@Getter
@Setter
@ToString
@SQLDelete(sql = "UPDATE shopping_carts SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shoppingCart")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ShoppingCartItem> cartItems = new ArrayList<>();
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
