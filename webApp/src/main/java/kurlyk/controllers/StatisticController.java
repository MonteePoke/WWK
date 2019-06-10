package kurlyk.controllers;


import kurlyk.models.UsverProgressLabWork;
import kurlyk.services.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @GetMapping("/statistic-usver")
    public List<UsverProgressLabWork> getStatiscticByUserId(
            @RequestParam("usverId") Long usverId
    ) {
        return statisticService.getStatisticByUsverId(usverId);
    }

    @GetMapping("/statistic-usver-lab-work")
    public UsverProgressLabWork getStatiscticByUsverIdByLabWorkId(
            @RequestParam("usverId") Long usverId,
            @RequestParam("labWorkId") Long labWorkId
    ) {
        return statisticService.getStatisticByUsverIdAndLabId(usverId, labWorkId).orElseThrow(RuntimeException::new);
    }
}
