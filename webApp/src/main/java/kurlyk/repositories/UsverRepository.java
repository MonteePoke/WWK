package kurlyk.repositories;

import kurlyk.models.Usver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsverRepository extends JpaRepository<Usver, Long> {

    Optional<Usver> findOneByLogin(String login);
}
