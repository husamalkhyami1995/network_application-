package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Statistics;
import net.javaguides.springboot.web.dto.ReceivedData;

import java.util.List;

public interface StatisticsService {
    void save(ReceivedData receivedData) ;
    List<Statistics> getAllStatistics() ;
}
