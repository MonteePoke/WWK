package kurlyk.services.statistic;

import kurlyk.models.UsverProgressLabWork;
import kurlyk.repositories.UsverProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private UsverProgressRepository usverProgressRepository;


    @Override
    public List<UsverProgressLabWork> getStatisticByUsverId(Long usverId) {
        return usverProgressRepository.findByUsverId(usverId);
    }

    @Override
    public Optional<UsverProgressLabWork> getStatisticByUsverIdAndLabId(Long usverId, Long labWorkId) {
        return usverProgressRepository.findOneByUsverIdAndLabWorkId(usverId, labWorkId);
    }
}
