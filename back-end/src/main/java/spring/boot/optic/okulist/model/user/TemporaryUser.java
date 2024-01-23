package spring.boot.optic.okulist.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("temporary")
public class TemporaryUser extends User {
    @Column(name = "session_id", nullable = false)
    private String sessionId;
}
