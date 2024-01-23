package spring.boot.optic.okulist.repository.lenses.paramsrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.lenses.parameters.Diopter;

@Repository
public interface DiopterRepository extends JpaRepository<Diopter,Long> {
}
