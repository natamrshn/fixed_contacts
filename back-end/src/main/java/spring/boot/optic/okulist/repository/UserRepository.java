package spring.boot.optic.okulist.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.RegisteredUser;
import spring.boot.optic.okulist.model.Role;

@Repository
public interface UserRepository extends JpaRepository<RegisteredUser, Long> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<RegisteredUser> findByEmail(String email);

    List<RegisteredUser> findUsersByRolesContainingAndIsDeletedFalse(Role role);
}
