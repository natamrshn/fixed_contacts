package spring.boot.optic.okulist.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.Glasses;

@Repository
public interface GlassesRepository extends JpaRepository<Glasses, Long> {
    List<Glasses> findAll(Specification<Glasses> specification);

    List<Glasses> findByColorIgnoreCaseAndNameAndPriceAndIdentifierAndDescriptionAndImageUrlAndImageUrlSecond(
            String color, String name, BigDecimal price, String identifier,
            String description, String imageUrl, String imageUrlSecond
    );

    List<Glasses> findAllByModelAndManufacturer(String model, String manufacturer);
}
