//package net.javaguides.springboot;
//
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.opencsv.CSVParser;
//import net.javaguides.springboot.model.Car;
//import net.javaguides.springboot.repository.CarRepository;
//import org.apache.commons.csv.CSVFormat;
////import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
////
////import com.car.shop.record.Car;
////import com.car.shop.repository.CarRepository;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class CarDataExportTest
//{
//
//	@Autowired
//	private TestEntityManager entityManager;
//
//	@Autowired
//	private CarRepository carRepository;
//
//
//	@Before
//	public void setUp()
//	{
//		try
//		{
//			InputStream    is         = new FileInputStream(new File("D:\\MOCK_DATA.csv"));
//			BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//			CSVParser csvParser  = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
//
//			 Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//
//			  for (CSVRecord csvRecord : csvRecords)
//			  {
//		    	  Car car = new Car(Integer.parseInt(csvRecord.get("id"))
//		    			  , csvRecord.get("carName")
//		    			  , Integer.parseInt(csvRecord.get("carPrice") )
//		    			  , Integer.parseInt(csvRecord.get("carseatNo") )
//		    			  , Integer.parseInt(csvRecord.get("version"))
//		    			  , csvRecord.get("carSellDate")
//		    			  , new Boolean(csvRecord.get("carSold").equals("1") ) );
//
//
//		    	  entityManager.persist(car);
//		    	  entityManager.flush();
//		      }
//
//			  csvParser.close();
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//
//	}
//
//	@Test
//	public void CheckSoldCarNo() throws Exception
//	{
//		List <Car> cars = new ArrayList<Car>();
//		carRepository.findByCarSoldTrueAndCarSellDateGreaterThanAndCarSellDateLessThan("2020-01-01", "2020-01-31").forEach(car -> cars.add(car));;
//
//		System.out.println("================================================================");
//		System.out.println("     Between Two Dates cars NO : " + cars.size() 		         );
//		System.out.println("================================================================");
//
//	}
//}
