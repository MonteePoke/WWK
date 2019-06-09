package kurlyk.repositories;


import kurlyk.models.Token;
import kurlyk.models.Usver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findOneByValue(String value);
    List<Token> findAllByUsver(Usver usver);
}
