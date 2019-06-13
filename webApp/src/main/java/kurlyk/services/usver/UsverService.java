package kurlyk.services.usver;

import kurlyk.model.Usver;

import java.util.List;


public interface UsverService {
    void signUp(Usver usver);

    List<Usver> findAll();

    Usver findOne(Long usverId);
}
