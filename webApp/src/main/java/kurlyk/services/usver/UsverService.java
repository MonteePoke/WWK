package kurlyk.services.usver;

import kurlyk.models.Usver;

import java.util.List;


public interface UsverService {
    void signUp(Usver usver);

    List<Usver> findAll();

    Usver findOne(Long usverId);
}
