package lecture2;

import org.testng.annotations.Test;

import lecture2POJOResource.POJO_Class;

import static io.restassured. RestAssured.*;
import static io.restassured.matcher. RestAssuredMatchers.*;
import static org.hamcrest. Matchers.*;

public class PostDataUsingPOJO {
	
	@Test(priority=1)
	void PostDatausingPOJO() {
		
		POJO_Class data = new POJO_Class();
		
		data.setId("4");
		data.setName("Scott");
		data.setLocation("New Zealand");
		data.setPhone("652254152");
		
		String coursesArr[] = {"Java","Selenium"};
		
		data.setcourses(coursesArr);
		
		given()
		.contentType("application/json")
		.body(data) // need to pass string variable from JSON data
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("id", equalTo("4"))
		.body("name", equalTo("Scott"))
		.body("location", equalTo("New Zealand"))
		.body("phone", equalTo("652254152"))
		.body("courses[0]", equalTo("Java"))
		.body("courses[1]", equalTo("Selenium"))
		.header("content-type", "application/json")
		.log().all();
		
	}
	
	@Test(priority=2,dependsOnMethods= {"PostDatausingPOJO"})
	void GetAllUserData() {
		
		given()
		
		.when()
		.get("http://localhost:3000/students")
			
		.then()
		.statusCode(200)
		.log().all();
		
		
	}
	
	@Test(priority=3)
	void DeleteUserData() {
		
		given()
		
		.when()
		.delete("http://localhost:3000/students/4")
		
		.then().statusCode(200)
		.log().all();
		
	}
	
}
