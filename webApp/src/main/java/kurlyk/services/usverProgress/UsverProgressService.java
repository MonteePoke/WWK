package kurlyk.services.usverProgress;

import kurlyk.model.UsverLabWorkAccess;
import kurlyk.model.UsverProgressLabWork;
import kurlyk.model.UsverProgressQuestion;
import kurlyk.transfer.UsverLabWorkDto;

import java.util.List;
import java.util.Optional;


public interface UsverProgressService {

    Optional<UsverProgressLabWork> getUsverProgress(Long usverId,Long labWorkId);
    Long saveUsverProgress(UsverProgressLabWork usverProgressLabWork);
    void deleteUsverProgress(Long id);

    void deleteUsverProgressLabWorkByLabWorkId(Long labWorkId);

    List<UsverProgressQuestion> getUsverProgressQuestions(Long labWorkId);

    Optional<UsverLabWorkAccess> getUsverLabWorkAccess(UsverLabWorkDto usverLabWorkDto);
    Long saveUsverLabWorkAccess(UsverLabWorkAccess usverLabWorkAccess);
    void deleteUsverLabWorkAccess(Long id);

    Long saveUsverProgressQuestion(UsverProgressQuestion usverProgressQuestion);
}
