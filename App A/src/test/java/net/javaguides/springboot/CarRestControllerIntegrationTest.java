//package net.javaguides.springboot;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.codehaus.jackson.map.ObjectMapper;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.car.shop.record.Car;
//
//
//
////@WebMvcTest(controllers  = CarRestController.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class CarRestControllerIntegrationTest
//{
//
//	@Autowired
//	private MockMvc    mvc;
//
//
//	@Before
//	public void setUp()
//	{
//		try
//		{
//			mvc.perform( MockMvcRequestBuilders
//					.post("/car/save")
//					.content(asJsonString( new Car (2 , "before Update" , 10000 , 0  ) ))
//					.contentType(MediaType.APPLICATION_JSON)
//					.accept(MediaType.APPLICATION_JSON))
//			.andExpect(status().isCreated())
//			.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//	}
//
//	@Test
//	public void saveCarTest()
//	{
//		try
//		{
//
//			mvc.perform( MockMvcRequestBuilders
//					.post("/car/save")
//					.content(asJsonString(new Car("insertedCar", 20000 , 0 , "" , 0 , "" , 0) ))
//					.contentType(MediaType.APPLICATION_JSON)
//					.accept(MediaType.APPLICATION_JSON))
//			.andExpect(status().isCreated())
//			.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//
//	}
//
//	@Test
//	public void updateCarTest()
//	{
//		try
//		{
//			mvc.perform( MockMvcRequestBuilders
//					.put("/car/update/{id}", 3)
//					.content(asJsonString( new Car("updatedCar", 10000 , 0 , "" , 0 , "" , 0) ))
//					.contentType(MediaType.APPLICATION_JSON)
//					.accept(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk())
//			.andExpect(MockMvcResultMatchers.jsonPath("$.carName").value("updatedCar"));
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//
//	}
//
//	@Test
//	public void deleteCarTest()
//	{
//		try
//		{
//
//			mvc.perform(MockMvcRequestBuilders
//					.delete("/car/delete/{id}" , 2))
//			.andExpect(status().isAccepted() );
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}
//
//	public static String asJsonString(final Object obj)
//	{
//		try
//		{
//			return new ObjectMapper().writeValueAsString(obj);
//		}
//		catch (Exception e)
//		{
//			throw new RuntimeException(e);
//		}
//	}
//
//}
