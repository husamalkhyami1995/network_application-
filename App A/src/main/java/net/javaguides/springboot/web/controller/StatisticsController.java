package net.javaguides.springboot.web.controller;

import net.javaguides.springboot.Facade.ProducerImplementation;
import net.javaguides.springboot.service.CarService;
import net.javaguides.springboot.service.ParameterService;
import net.javaguides.springboot.service.StatisticsService;
import net.javaguides.springboot.web.dto.StatisticsInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class StatisticsController {


    // global parameters

    private CarService carService ;
    private StatisticsService statisticsService ;
    private ParameterService parameterService ;
    private final ProducerImplementation producerImplementation;

    public StatisticsController(CarService carService, ProducerImplementation producerImplementation
            , StatisticsService statisticsService, ParameterService parameterService ) {
        super() ;
        this.carService = carService ;
        this.producerImplementation = producerImplementation ;
        this.statisticsService = statisticsService ;
        this.parameterService = parameterService ;
    }


    @GetMapping("/statistics")
    public String statistics(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StatisticsInfo statisticsInfo = new StatisticsInfo() ;
        statisticsInfo.setEmail( authentication.getName() );
        model.addAttribute("statisticInfo", statisticsInfo) ;
        return "statistics";
    }

    @PostMapping("/statistics")
    public String getResult(@ModelAttribute StatisticsInfo statisticsInfo ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String message = authentication.getName() + "#" + statisticsInfo.getContent() + '#' + statisticsInfo.getDate();
        this.producerImplementation.sendMessage( message );
        return "redirect:/result" ;

    }

}
