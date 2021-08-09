package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByName( String carName ) ;
}
