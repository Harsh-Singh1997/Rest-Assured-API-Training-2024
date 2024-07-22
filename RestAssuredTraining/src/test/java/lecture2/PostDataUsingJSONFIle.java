package lecture2;

import static io.restassured. RestAssured.*;
import static io.restassured.matcher. RestAssuredMatchers.*;
import static org.hamcrest. Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class PostDataUsingJSONFIle {
	
	@Test(priority=1)
	public void PostDataUsingJsonFile() throws FileNotFoundException {

	File  fi= new File("G:\\Eclipse Workspaces\\RestAssuredAPI Training\\RestAssuredTraining\\StudentData.json");
	
	FileReader fr = new FileReader(fi);
	
	JSONTokener jt = new JSONTokener(fr);
	
	JSONObject data = new JSONObject(jt);
	
	String coursear[] = {"Python","Pytest"};
	
	data.put("courses", coursear);
	
	given()
	.contentType("application/json")
	.body(data.toString())
	
	.when()
	.post("http://localhost:3000/students")
	
	.then()
	.statusCode(201)
	.log().all();
		
	}
	
	@Test(priority=2,dependsOnMethods="PostDataUsingJsonFile")
	public void DeleteUserData() {
		
		given()
		
		.when()
		.delete("http://localhost:3000/students/4")
		
		.then()
		.statusCode(200)
		.log().all();
		
	}
	
}
	
	