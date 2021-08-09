/**
 * Created by =>
 * User: Kinan
 * Date: 7/5/2020
 * Time: 4:07 AM
 **/
package com.karam.applicationB.Facade;

import com.karam.applicationB.dto.ReceivedData;
import com.karam.applicationB.models.Car;
import com.karam.applicationB.repository.CarRepository;
import com.karam.applicationB.services.StatisticsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class messageListener {
    private CarRepository carRepository ;
    private StatisticsService statisticsService ;
    public messageListener(CarRepository carRepository, StatisticsService statisticsService ) {
        this.carRepository = carRepository ;
        this.statisticsService = statisticsService ;
    }

    @RabbitListener(queues = "message_queue")
    public void receiveMessage( String statisticsInfo ) {

        String [] information = statisticsInfo.split("#") ;
        String email = information[0] ;
        String content = information[1] ;
        String date = information[2] ;
        String [] dateToArray = date.split("-") ;
        String year = dateToArray[0] ;
        String month = dateToArray[1] ;
        List<Car> allCars = this.carRepository.findAll() ;
        StringBuilder result = new StringBuilder();
        Float total = 0.0f ;
        for(Car car : allCars ) {
            String currentDate = car.getSellDate() ;
            if ( currentDate == null ) continue ;
            String [] currentDateToArray = currentDate.split("-") ;
            String currentYear = currentDateToArray[0] ;
            String currentMonth = currentDateToArray[1] ;
            if ( currentYear.equals( year ) && currentMonth.equals( month )) {
                result.append(car.getId().toString());
                result.append(",") ;
                total += car.getSellPrice() ;
            }
//            System.out.println(receivedData.toString());
        }
        ReceivedData receivedData = new ReceivedData( email , content , result.toString(), total ) ;
        this.statisticsService.save( receivedData );

    }

}

