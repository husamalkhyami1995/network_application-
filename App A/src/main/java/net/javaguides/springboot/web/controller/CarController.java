package net.javaguides.springboot.web.controller;

import net.javaguides.springboot.Facade.ProducerImplementation;
import net.javaguides.springboot.model.Car;
import net.javaguides.springboot.model.Parameter;
import net.javaguides.springboot.service.CarService;
import net.javaguides.springboot.service.ParameterService;
import net.javaguides.springboot.service.StatisticsService;
import net.javaguides.springboot.web.dto.CarInfo;
import net.javaguides.springboot.web.dto.SelledCar;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {


    // global parameters

    private CarService carService ;
    private StatisticsService statisticsService ;
    private ParameterService parameterService ;
    private final ProducerImplementation producerImplementation;

    public CarController(CarService carService, ProducerImplementation producerImplementation
            , StatisticsService statisticsService, ParameterService parameterService ) {
        super() ;
        this.carService = carService ;
        this.producerImplementation = producerImplementation ;
        this.statisticsService = statisticsService ;
        this.parameterService = parameterService ;
    }


//    @GetMapping("/")
//    public String home(Model model ) {
//        model.addAttribute("cars", this.carService.getAllCars()) ;
//        return "index";
//    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new CarInfo()) ;
        return "create";
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
    public String edit(@PathVariable Long id , Model model) {
        model.addAttribute("car", this.carService.findById( id )) ;
        return "edit";
    }

    @RequestMapping(value="/delete/{id}", method= RequestMethod.GET)
    public String delete(@PathVariable Long id ) {
        this.carService.delete(id);
        return "redirect:/?delete";
    }

    @PostMapping("/create")
    public String createCar(@ModelAttribute CarInfo carInfo ) {
        if ( carInfo.getSeats() == null ) {
            Parameter parameter = this.getParameter() ;
            carInfo.setSeats((long) parameter.getSeatNumber());
        }
        this.carService.create(carInfo) ;
        return "redirect:/?create" ;
    }

    @PostMapping("/edit")
    public String editCar(@ModelAttribute Car car ) {
        if ( car.getSeats() == null ) {
            Parameter parameter = this.getParameter() ;
            car.setSeats((long) parameter.getSeatNumber());
        }
        this.carService.update(car) ;
        return "redirect:/?edit" ;
    }
    @GetMapping("/sell")
    public String sell(Model model) {
        model.addAttribute("selledCar", new SelledCar()) ;
        model.addAttribute("cars", this.carService.getUnSelledCars()) ;
        return "sell";
    }

    @PostMapping("/sell")
    public String createCar(@ModelAttribute SelledCar selledCar , Model model ) {
        try{
            this.carService.sell(selledCar, this.getParameter().getBenefit() ) ;
        }catch(OptimisticLockingFailureException e ) {
            model.addAttribute("lockError", "there is another operation...") ;
        }
        return "redirect:/?sell" ;
    }


    private Parameter getParameter () {
        return this.parameterService.read().get(0) ;
    }

}
