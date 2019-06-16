package kurlyk.services.usver;

import kurlyk.model.Usver;

import java.util.List;
import java.util.Optional;


public interface UsverService {
    void signUp(Usver usver);

    List<Usver> findAll();

    Optional<Usver> findById(Long usverId);
}
