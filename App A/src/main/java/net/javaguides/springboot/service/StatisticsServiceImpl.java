package net.javaguides.springboot.service;


import net.javaguides.springboot.model.Statistics;
import net.javaguides.springboot.repository.StatisticsRepository;
import net.javaguides.springboot.web.dto.ReceivedData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private StatisticsRepository statisticsRepository ;
    public StatisticsServiceImpl( StatisticsRepository statisticsRepository ) {
        this.statisticsRepository = statisticsRepository ;
    }
    @Override
    public void save(ReceivedData receivedData) {
        Statistics statistics =
                new Statistics(receivedData.getEmail(), receivedData.getContent(), receivedData.getIds(), receivedData.getSum()) ;
        this.statisticsRepository.save( statistics ) ;
    }

    @Override
    public List<Statistics> getAllStatistics() {
        return this.statisticsRepository.findAll() ;
    }

}
