package com.karam.applicationB.repository;

import com.karam.applicationB.models.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  StatisticsRepository extends JpaRepository<Statistics, Long> {
}
