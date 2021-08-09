package net.javaguides.springboot.web.controller;

import net.javaguides.springboot.Facade.ProducerImplementation;
import net.javaguides.springboot.service.CarService;
import net.javaguides.springboot.service.ParameterService;
import net.javaguides.springboot.service.StatisticsService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class AuthenticationController {

    // global parameters

    private CarService carService ;
    private StatisticsService statisticsService ;
    private ParameterService parameterService ;
    private final ProducerImplementation producerImplementation;

    public AuthenticationController(CarService carService, ProducerImplementation producerImplementation
            , StatisticsService statisticsService, ParameterService parameterService ) {
        super() ;
        this.carService = carService ;
        this.producerImplementation = producerImplementation ;
        this.statisticsService = statisticsService ;
        this.parameterService = parameterService ;
    }

    @GetMapping("/login")
    public String login() {
        // prevent back to login if you are already logged in
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
        if ( authentication == null || authentication instanceof AnonymousAuthenticationToken) {

            // return template_name // template/*.html
            return "login";
        }
        else{
//            System.out.println("Testing 8081 ...");
            return "redirect:/";}

    }

}
