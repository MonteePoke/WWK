package kurlyk.services.usverProgress;

import kurlyk.models.UsverLabWorkAccess;
import kurlyk.models.UsverProgressLabWork;
import kurlyk.transfer.UsverLabWorkDto;

import java.util.Optional;


public interface UsverProgressService {

    Long saveUsverProgress(UsverProgressLabWork usverProgressLabWork);
    void deleteUsverProgress(Long id);

    Optional<UsverLabWorkAccess> getUsverLabWorkAccess(UsverLabWorkDto usverLabWorkDto);
    Long saveUsverLabWorkAccess(UsverLabWorkAccess usverLabWorkAccess);
    void deleteUsverLabWorkAccess(Long id);
}
