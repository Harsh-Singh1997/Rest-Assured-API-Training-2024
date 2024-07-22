package lecture2;

import org.testng.annotations.Test;

import static io.restassured. RestAssured.*;
import static io.restassured.matcher. RestAssuredMatchers.*;
import static org.hamcrest. Matchers.*;

import java.util.HashMap;

public class PostDataUsingHashMap {

	@Test(priority=1)
	void PostData() {
		
		HashMap data = new HashMap();
		data.put("id", "4");
		data.put("name", "Harsh");
		data.put("location", "Italy");
		data.put("phone", "562212541");
		
		String coursedata[]= {"Pytest","Python"};
		
		data.put("courses", coursedata);
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("id", equalTo("4"))
		.body("name", equalTo("Harsh"))
		.body("location", equalTo("Italy"))
		.body("phone", equalTo("562212541"))
		.body("courses[0]", equalTo("Pytest"))
		.body("courses[1]", equalTo("Python"))
		.header("content-type", "application/json")
		.log().all();
		
	}
	
	@Test(priority=2,dependsOnMethods= {"PostData"})
	void DeleteData() {
		
		given()
		
		.when()
		.delete("http://localhost:3000/students/4")
		
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority=3)
	void GetUserData() {
		
		given()
		
		.when()
		.get("http://localhost:3000/students/4")
		
		.then()
		.statusCode(404)
		.log().all();
		
	}
	
}
