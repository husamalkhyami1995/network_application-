package net.javaguides.springboot.web.controller;

import net.javaguides.springboot.Facade.ProducerImplementation;
import net.javaguides.springboot.model.Parameter;
import net.javaguides.springboot.service.CarService;
import net.javaguides.springboot.service.ParameterService;
import net.javaguides.springboot.service.StatisticsService;
import net.javaguides.springboot.web.dto.ParameterInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class ParameterController {


    // global parameters

    private CarService carService ;
    private StatisticsService statisticsService ;
    private ParameterService parameterService ;
    private final ProducerImplementation producerImplementation;

    public ParameterController(CarService carService, ProducerImplementation producerImplementation
            , StatisticsService statisticsService, ParameterService parameterService ) {
        super() ;
        this.carService = carService ;
        this.producerImplementation = producerImplementation ;
        this.statisticsService = statisticsService ;
        this.parameterService = parameterService ;
    }


    @GetMapping("/parameter")
    public String parameter(Model model) {
        // just one record inside database
        Parameter parameter = this.parameterService.read().get(0) ;
        model.addAttribute("parameter", parameter ) ;
        return "parameter";
    }

    @PostMapping("/parameter/edit")
    public String editCar(@ModelAttribute Parameter parameter ) {
        System.out.println( parameter.getSeatNumber() + " => " + parameter.getBenefit() );
        ParameterInfo parameterInfo =
                new ParameterInfo( parameter.getId(), parameter.getSeatNumber() , parameter.getBenefit()) ;
        this.parameterService.edit( parameterInfo );
        return "redirect:/parameter" ;
    }
}
