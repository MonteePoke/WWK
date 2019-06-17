package kurlyk.services.statistic;

import kurlyk.model.UsverProgressLabWork;
import kurlyk.repositories.UsverProgressLabWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private UsverProgressLabWorkRepository usverProgressLabWorkRepository;


    @Override
    public List<UsverProgressLabWork> getStatisticByUsverId(Long usverId) {
        return usverProgressLabWorkRepository.findByUsverId(usverId);
    }

    @Override
    public Optional<UsverProgressLabWork> getStatisticByUsverIdAndLabId(Long usverId, Long labWorkId) {
        return usverProgressLabWorkRepository.findOneByUsverIdAndLabWorkId(usverId, labWorkId);
    }
}
