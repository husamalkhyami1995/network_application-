package com.karam.applicationB.services;

import com.karam.applicationB.dto.ReceivedData;
import com.karam.applicationB.models.Statistics;
import com.karam.applicationB.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

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
}
