//package net.javaguides.springboot.web;
//
//import net.javaguides.springboot.Facade.ProducerImplementation;
//import net.javaguides.springboot.model.Car;
//import net.javaguides.springboot.model.Parameter;
//import net.javaguides.springboot.model.Statistics;
//import net.javaguides.springboot.service.CarService;
//import net.javaguides.springboot.service.ParameterService;
//import net.javaguides.springboot.service.StatisticsService;
//import net.javaguides.springboot.web.dto.CarInfo;
//import net.javaguides.springboot.web.dto.ParameterInfo;
//import net.javaguides.springboot.web.dto.SelledCar;
//import net.javaguides.springboot.web.dto.StatisticsInfo;
//import org.springframework.dao.OptimisticLockingFailureException;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.supercsv.io.CsvBeanWriter;
//import org.supercsv.io.ICsvBeanWriter;
//import org.supercsv.prefs.CsvPreference;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//public class MainController {
//
//	// global parameters
//
//	private CarService carService ;
//	private StatisticsService statisticsService ;
//	private ParameterService parameterService ;
//	private final ProducerImplementation producerImplementation;
//
//	public MainController(CarService carService, ProducerImplementation producerImplementation
//	, StatisticsService statisticsService, ParameterService parameterService ) {
//		super() ;
//		this.carService = carService ;
//		this.producerImplementation = producerImplementation ;
//		this.statisticsService = statisticsService ;
//		this.parameterService = parameterService ;
//	}
//	@GetMapping("/login")
//	public String login(Model model ) {
//		// prevent back to login if you are already logged in
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
//		if ( authentication == null || authentication instanceof AnonymousAuthenticationToken)
//			return "login" ;
//		else
//			return "redirect:/";
//	}
////
//	@GetMapping("/")
//	public String home(Model model ) {
//		model.addAttribute("cars", this.carService.getAllCars()) ;
//		return "index";
//	}
//
//	@GetMapping("/create")
//	public String create(Model model) {
//		model.addAttribute("car", new CarInfo()) ;
//		return "create";
//	}
//
//	@RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
//	public String edit(@PathVariable Long id , Model model) {
//		model.addAttribute("car", this.carService.findById( id )) ;
//		return "edit";
//	}
//
//	@RequestMapping(value="/delete/{id}", method= RequestMethod.GET)
//	public String delete(@PathVariable Long id ) {
//		this.carService.delete(id);
//		return "redirect:/?delete";
//	}
//
//	@PostMapping("/create")
//	public String createCar(@ModelAttribute CarInfo carInfo ) {
//		if ( carInfo.getSeats() == null ) {
//			Parameter parameter = this.getParameter() ;
//			carInfo.setSeats((long) parameter.getSeatNumber());
//		}
//		this.carService.create(carInfo) ;
//		return "redirect:/?create" ;
//	}
//
//	@PostMapping("/edit")
//	public String editCar(@ModelAttribute Car car ) {
//		if ( car.getSeats() == null ) {
//			Parameter parameter = this.getParameter() ;
//			car.setSeats((long) parameter.getSeatNumber());
//		}
//		this.carService.update(car) ;
//		return "redirect:/?edit" ;
//	}
//	@GetMapping("/sell")
//	public String sell(Model model) {
//		model.addAttribute("selledCar", new SelledCar()) ;
//		model.addAttribute("cars", this.carService.getUnSelledCars()) ;
//		return "sell";
//	}
//
//	@PostMapping("/sell")
//	public String createCar(@ModelAttribute SelledCar selledCar , Model model ) {
//		try{
//			this.carService.sell(selledCar, this.getParameter().getBenefit() ) ;
//		}catch(OptimisticLockingFailureException e ) {
//			model.addAttribute("lockError", "there is another operation...") ;
//		}
//		return "redirect:/?sell" ;
//	}
//
//	@GetMapping("/statistics")
//	public String statistics(Model model) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		StatisticsInfo statisticsInfo = new StatisticsInfo() ;
//		statisticsInfo.setEmail( authentication.getName() );
//		model.addAttribute("statisticInfo", statisticsInfo) ;
//		return "statistics";
//	}
//
//	@PostMapping("/statistics")
//	public String getResult(@ModelAttribute StatisticsInfo statisticsInfo ) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String message = authentication.getName() + "#" + statisticsInfo.getContent() + '#' + statisticsInfo.getDate();
//		this.producerImplementation.sendMessage( message );
//		return "redirect:/result" ;
//
//	}
//
//	@GetMapping("/result")
//	public String result (Model model ) {
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
//		if ( statistics == null || statistics.getIds().length() == 0 ) {
//			List<Car> cars = new ArrayList<>() ;
//			model.addAttribute("cars" , cars ) ;
//			model.addAttribute("total", 0 ) ;
//			model.addAttribute("error" , true )  ;
//			return "result" ;
//		}
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
//		model.addAttribute("cars", result ) ;
//		model.addAttribute("total",statistics.getSum()) ;
//		return "result" ;
//	}
//	@GetMapping("/export")
//	public void exportToCSV(HttpServletResponse response) throws IOException {
//		response.setContentType("text/csv");
//		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//		String currentDateTime = dateFormatter.format(new Date());
//
//		String headerKey = "Content-Disposition";
//		String headerValue = "attachment; filename=statistics_" + currentDateTime + ".csv";
//		response.setHeader(headerKey, headerValue);
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
//
//		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
//		String[] csvHeader = {"Car ID", "Car Name", "Price", "Seats Number", "Sell Date", "Sell Price", "Customer"};
//		String[] nameMapping = {"id", "name", "price", "seats", "sellDate", "sellPrice", "customer"};
//
//		csvWriter.writeHeader(csvHeader);
//
//		for (Car car: result) {
//			csvWriter.write(car, nameMapping);
//		}
//
//		csvWriter.close();
//	}
////
////	@GetMapping("/export")
////	public void exportToCSV(HttpServletResponse response) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
////
////		// _-------------------------------------------------------------------------------
////		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////		List<Statistics> allStatistics = this.statisticsService.getAllStatistics() ;
////		Statistics statistics = null ;
////		for( int i = allStatistics.size() - 1 ; i >= 0  ; --i ) {
////			if ( allStatistics.get(i).getEmail().equals( authentication.getName())) {
////				statistics = allStatistics.get(i) ;
////				break ;
////			}
////		}
////
////		String [] stringIds = statistics.getIds().split(",") ;
////
////		Long [] ids = new Long[ stringIds.length ] ;
////		for( int i = 0 ; i < ids.length ; ++i ) {
////			ids[i] = Long.valueOf( stringIds[i]) ;
////		}
////		Arrays.sort(ids) ;
////		List<Car> cars = this.carService.getAllCars() ;
////		List<Car> result = new ArrayList<>() ;
////		Long totalSum = 0L ;
////		for(Car car : cars ) {
////			int index = Arrays.binarySearch( ids, car.getId()) ;
////			if ( index >= 0 ) {
////				result.add( car )  ;
////			}
////		}
////
////		// _-------------------------------------------------------------------------------
////
////		//set file name and content type
////		String filename = "statistics.csv";
////
////		response.setContentType("text/csv");
////		response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
////				"attachment; filename=\"" + filename + "\"");
////
////		//create a csv writer
////		StatefulBeanToCsv<Car> writer = new StatefulBeanToCsvBuilder<Car>(response.getWriter())
////				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
////				.withSeparator(CSVWriter.DEFAULT_SEPARATOR)
////				.withOrderedResults(false)
////				.build();
////
////		//write all users to csv file
////		writer.write(result);
////	}
//
//	@GetMapping("/parameter")
//	public String parameter(Model model) {
//		// just one record inside database
//		Parameter parameter = this.parameterService.read().get(0) ;
//		model.addAttribute("parameter", parameter ) ;
//		return "parameter";
//	}
//
//	@PostMapping("/parameter/edit")
//	public String editCar(@ModelAttribute Parameter parameter ) {
//		System.out.println( parameter.getSeatNumber() + " => " + parameter.getBenefit() );
//		ParameterInfo parameterInfo =
//				new ParameterInfo( parameter.getId(), parameter.getSeatNumber() , parameter.getBenefit()) ;
//		this.parameterService.edit( parameterInfo );
//		return "redirect:/parameter" ;
//	}
//
//	private Parameter getParameter () {
//		return this.parameterService.read().get(0) ;
//	}
//
//}
