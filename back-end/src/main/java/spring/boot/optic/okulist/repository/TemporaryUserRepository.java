package spring.boot.optic.okulist.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.user.TemporaryUser;

@Repository
public interface TemporaryUserRepository extends JpaRepository<TemporaryUser, Long> {
    Optional<TemporaryUser> findBySessionId(String sessionId);
}
