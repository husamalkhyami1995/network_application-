package com.karam.applicationB.services;

import com.karam.applicationB.dto.CarInfo;
import com.karam.applicationB.dto.SelledCar;
import com.karam.applicationB.models.Car;
import com.karam.applicationB.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository ;

    public CarServiceImpl( CarRepository carRepository ) {
        super() ;
        this.carRepository = carRepository ;
    }
    // this for create new car and save it in database
    @Override
    public Car create(CarInfo car) {
        Car createdCar = new Car( car.getName(), car.getPrice(), car.getSeats(), null, null , null ) ;
        return this.carRepository.save(createdCar) ;
    }

    @Override
    public Car update(Car car) {
        System.out.println(car.getId());
        return this.carRepository.save(car) ;
    }

    @Override
    public void delete(Long id) {
        this.carRepository.deleteById(id);
    }

    @Override
    public List<Car> getAllUsers() {
        return this.carRepository.findAll() ;
    }

    @Override
    public Car findById(Long id) {
        return this.carRepository.findById(id).get() ;
    }

    @Override
    public List<Car> getUnSelledCars() {
        List<Car> allCars = this.carRepository.findAll() ;
        List<Car> result = new ArrayList<>() ;
        for(Car car : allCars ) {
            if ( car.getSellDate() == null ) result.add( car ) ;
        }
        return result ;
    }

    @Override
    public void sell(SelledCar selledCar, Float benefit ) {
        Car car = this.carRepository.findById( selledCar.getId()).get() ;
        car.setSellDate( selledCar.getSellDate());
        car.setSellPrice(car.getPrice() + ( car.getPrice() * benefit ));
        car.setCustomer(selledCar.getCustomer());
        this.carRepository.save(car) ;

    }
}
