package kurlyk.services.usverProgress;

import kurlyk.model.UsverLabWorkAccess;
import kurlyk.model.UsverProgressLabWork;
import kurlyk.transfer.UsverLabWorkDto;

import java.util.Optional;


public interface UsverProgressService {

    Long saveUsverProgress(UsverProgressLabWork usverProgressLabWork);
    void deleteUsverProgress(Long id);

    Optional<UsverLabWorkAccess> getUsverLabWorkAccess(UsverLabWorkDto usverLabWorkDto);
    Long saveUsverLabWorkAccess(UsverLabWorkAccess usverLabWorkAccess);
    void deleteUsverLabWorkAccess(Long id);
}
