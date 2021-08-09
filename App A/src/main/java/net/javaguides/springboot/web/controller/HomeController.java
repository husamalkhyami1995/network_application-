package net.javaguides.springboot.web.controller;

import net.javaguides.springboot.Facade.ProducerImplementation;
import net.javaguides.springboot.service.CarService;
import net.javaguides.springboot.service.ParameterService;
import net.javaguides.springboot.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {

    // global parameters

    private CarService carService ;


    public HomeController(CarService carService) {
        super() ;
        this.carService = carService ;
    }
    @GetMapping("/")
    public String home(Model model ) {
        model.addAttribute("cars", this.carService.getAllCars()) ;
        return "index";
    }
}
