package kurlyk.services.statistic;

import kurlyk.models.UsverProgressLabWork;

import java.util.List;
import java.util.Optional;


public interface StatisticService {

    List<UsverProgressLabWork> getStatisticByUsverId(Long usverId);
    Optional<UsverProgressLabWork> getStatisticByUsverIdAndLabId(Long usverId, Long labWorkId);

}
