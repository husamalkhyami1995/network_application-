package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Car;
import net.javaguides.springboot.web.dto.CarInfo;
import net.javaguides.springboot.web.dto.SelledCar;

import java.util.List;

public interface CarService {
    Car create(CarInfo car) ;
    Car update(Car car) ;
    void delete(Long id ) ;
    List<Car> getAllCars() ;
    Car findById(Long id ) ;
    List<Car> getUnSelledCars() ;
    void sell(SelledCar selledCar, Float benefit ) ;

}
