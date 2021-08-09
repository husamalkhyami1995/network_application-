package com.karam.applicationB.services;


import com.karam.applicationB.dto.CarInfo;
import com.karam.applicationB.dto.SelledCar;
import com.karam.applicationB.models.Car;

import java.util.List;

public interface CarService {
    Car create(CarInfo car) ;
    Car update(Car car) ;
    void delete(Long id) ;
    List<Car> getAllUsers() ;
    Car findById(Long id) ;
    List<Car> getUnSelledCars() ;
    void sell(SelledCar selledCar, Float benefit) ;

}
