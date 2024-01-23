package spring.boot.optic.okulist.repository.lenses.paramsrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.lenses.parameters.Cylinder;

@Repository
public interface CylinderRepository extends JpaRepository<Cylinder,Long> {
}
