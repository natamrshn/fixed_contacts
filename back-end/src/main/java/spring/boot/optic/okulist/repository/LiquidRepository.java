package spring.boot.optic.okulist.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.Liquid;

@Repository
public interface LiquidRepository extends JpaRepository<Liquid, Long> {
    List<Liquid> findAll(Specification<Liquid> specification);

    List<Liquid> findByVolumeNotAndPriceNotAndNameAndIdentifierAndDescriptionAndImageUrlAndImageUrlSecond(
            int volume, BigDecimal price, String name,
            String identifier, String description, String imageUrl, String imageUrlSecond
    );

    List<Liquid> findAllByIdentifier(String identifier);
}
