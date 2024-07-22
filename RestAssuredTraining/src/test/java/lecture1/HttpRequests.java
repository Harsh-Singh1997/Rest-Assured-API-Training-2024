package lecture1;

import org.testng.annotations.Test;

import static io.restassured. RestAssured.*;
import static io.restassured.matcher. RestAssuredMatchers.*;
import static org.hamcrest. Matchers.*;

import java.util.HashMap;


/* 
given ()
content type, set cookies, add auth, add param, set headers info etc....

when ()
get, post, put, delete

then ()
validate status code, extract response, extract headers cookies & response body....
*/

public class HttpRequests {
	
	int id;
	
	@Test(priority=1)
	void GetUsers(){
		
		given()
		
		.when()
		.get("https://reqres.in/api/users?page=2")
		
		.then()
		.statusCode(200)
		.body("page",equalTo(2))
		.log().all();		
	}
	
	@Test(priority=2)
	void CreateUser() {
		
		HashMap data = new HashMap();
		data.put("name", "Harsh");
		data.put("job", "Trainee");
		
		
		id=given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
		/*
		.then()
		.statusCode(201)
		.log().all();		
		*/
	}
	
	@Test(priority=3,dependsOnMethods={"CreateUser"})
	void UpdateUser() {
		
		HashMap data2 = new HashMap();
		data2.put("name", "Joseph");
		data2.put("job", "Java-Engineer");
		
		given()
		.contentType("application/json")
		.body(data2)
		
		.when()
		.put("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(200)
		.log().all();
		
	}
	
	@Test(priority=4)
	void DeleteUser() {
		
		given()
		
		.when()
		.delete("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(204)
		.log().all();
		
	}
	
	@Test(priority=4,dependsOnMethods= {"DeleteUser"})
	void GetIdUser() {
		
		given()
		
		.when()
		.get("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(404)
		.log().all();
	}

}
