package net.javaguides.springboot.web.controller;

import net.javaguides.springboot.Facade.ProducerImplementation;
import net.javaguides.springboot.model.Car;
import net.javaguides.springboot.model.Statistics;
import net.javaguides.springboot.service.CarService;
import net.javaguides.springboot.service.ParameterService;
import net.javaguides.springboot.service.StatisticsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller

public class ResultController {


    // global parameters

    private CarService carService ;
    private StatisticsService statisticsService ;
    private ParameterService parameterService ;
    private final ProducerImplementation producerImplementation;

    public ResultController(CarService carService, ProducerImplementation producerImplementation
            , StatisticsService statisticsService, ParameterService parameterService ) {
        super() ;
        this.carService = carService ;
        this.producerImplementation = producerImplementation ;
        this.statisticsService = statisticsService ;
        this.parameterService = parameterService ;
    }


    @GetMapping("/result")
    public String result (Model model ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Statistics> allStatistics = this.statisticsService.getAllStatistics() ;
        Statistics statistics = null ;
        for( int i = allStatistics.size() - 1 ; i >= 0  ; --i ) {
            if ( allStatistics.get(i).getEmail().equals( authentication.getName())) {
                statistics = allStatistics.get(i) ;
                break ;
            }
        }

        if ( statistics == null || statistics.getIds().length() == 0 ) {
            List<Car> cars = new ArrayList<>() ;
            model.addAttribute("cars" , cars ) ;
            model.addAttribute("total", 0 ) ;
            model.addAttribute("error" , true )  ;
            return "result" ;
        }
        String [] stringIds = statistics.getIds().split(",") ;

        Long [] ids = new Long[ stringIds.length ] ;
        for( int i = 0 ; i < ids.length ; ++i ) {
            ids[i] = Long.valueOf( stringIds[i]) ;
        }
        Arrays.sort(ids) ;
        List<Car> cars = this.carService.getAllCars() ;
        List<Car> result = new ArrayList<>() ;
        Long totalSum = 0L ;
        for(Car car : cars ) {
            int index = Arrays.binarySearch( ids, car.getId()) ;
            if ( index >= 0 ) {
                result.add( car )  ;
            }
        }

        model.addAttribute("cars", result ) ;
        model.addAttribute("total",statistics.getSum()) ;
        return "result" ;
    }
    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=statistics_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        // _-------------------------------------------------------------------------------
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Statistics> allStatistics = this.statisticsService.getAllStatistics() ;
        Statistics statistics = null ;
        for( int i = allStatistics.size() - 1 ; i >= 0  ; --i ) {
            if ( allStatistics.get(i).getEmail().equals( authentication.getName())) {
                statistics = allStatistics.get(i) ;
                break ;
            }
        }

        String [] stringIds = statistics.getIds().split(",") ;

        Long [] ids = new Long[ stringIds.length ] ;
        for( int i = 0 ; i < ids.length ; ++i ) {
            ids[i] = Long.valueOf( stringIds[i]) ;
        }
        Arrays.sort(ids) ;
        List<Car> cars = this.carService.getAllCars() ;
        List<Car> result = new ArrayList<>() ;
        Long totalSum = 0L ;
        for(Car car : cars ) {
            int index = Arrays.binarySearch( ids, car.getId()) ;
            if ( index >= 0 ) {
                result.add( car )  ;
            }
        }

        // _-------------------------------------------------------------------------------


        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Car ID", "Car Name", "Price", "Seats Number", "Sell Date", "Sell Price", "Customer"};
        String[] nameMapping = {"id", "name", "price", "seats", "sellDate", "sellPrice", "customer"};

        csvWriter.writeHeader(csvHeader);

        for (Car car: result) {
            csvWriter.write(car, nameMapping);
        }

        csvWriter.close();
    }

//	@GetMapping("/export")
//	public void exportToCSV(HttpServletResponse response) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
//
//		// _-------------------------------------------------------------------------------
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		List<Statistics> allStatistics = this.statisticsService.getAllStatistics() ;
//		Statistics statistics = null ;
//		for( int i = allStatistics.size() - 1 ; i >= 0  ; --i ) {
//			if ( allStatistics.get(i).getEmail().equals( authentication.getName())) {
//				statistics = allStatistics.get(i) ;
//				break ;
//			}
//		}
//
//		String [] stringIds = statistics.getIds().split(",") ;
//
//		Long [] ids = new Long[ stringIds.length ] ;
//		for( int i = 0 ; i < ids.length ; ++i ) {
//			ids[i] = Long.valueOf( stringIds[i]) ;
//		}
//		Arrays.sort(ids) ;
//		List<Car> cars = this.carService.getAllCars() ;
//		List<Car> result = new ArrayList<>() ;
//		Long totalSum = 0L ;
//		for(Car car : cars ) {
//			int index = Arrays.binarySearch( ids, car.getId()) ;
//			if ( index >= 0 ) {
//				result.add( car )  ;
//			}
//		}
//
//		// _-------------------------------------------------------------------------------
//
//		//set file name and content type
//		String filename = "statistics.csv";
//
//		response.setContentType("text/csv");
//		response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
//				"attachment; filename=\"" + filename + "\"");
//
//		//create a csv writer
//		StatefulBeanToCsv<Car> writer = new StatefulBeanToCsvBuilder<Car>(response.getWriter())
//				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//				.withSeparator(CSVWriter.DEFAULT_SEPARATOR)
//				.withOrderedResults(false)
//				.build();
//
//		//write all users to csv file
//		writer.write(result);
//	}

}
