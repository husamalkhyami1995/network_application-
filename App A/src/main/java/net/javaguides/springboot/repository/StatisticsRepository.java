package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  StatisticsRepository extends JpaRepository<Statistics, Long> {
}
