package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Car;
import net.javaguides.springboot.repository.CarRepository;
import net.javaguides.springboot.web.dto.CarInfo;
import net.javaguides.springboot.web.dto.SelledCar;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
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
        Car editedCar = this.carRepository.findById( car.getId()).get() ;
        editedCar.setName(car.getName());
        editedCar.setPrice(car.getPrice());
        editedCar.setSeats(car.getSeats());
        return this.carRepository.save(editedCar) ;
    }

    @Override
    public void delete(Long id) {
        this.carRepository.deleteById(id);
    }

    @Override
    public List<Car> getAllCars() {
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
    @Lock(LockModeType.OPTIMISTIC)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    public void sell(SelledCar selledCar, Float benefit ) {
        Car car = this.carRepository.findById( selledCar.getId()).get() ;
        car.setSellDate( selledCar.getSellDate());
        car.setSellPrice(car.getPrice() + ( car.getPrice() * benefit ));
        car.setCustomer(selledCar.getCustomer());
        this.carRepository.save(car) ;

    }
}
