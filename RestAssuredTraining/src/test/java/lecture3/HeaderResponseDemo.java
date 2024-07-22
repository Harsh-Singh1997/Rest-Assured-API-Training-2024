package lecture3;

import static io.restassured. RestAssured.*;
import static io.restassured.matcher. RestAssuredMatchers.*;
import static org.hamcrest. Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderResponseDemo {

	@Test(priority=1)
 void testHeader() {
	 
	 given()
	 
	 .when()
	 .get("https://www.google.com/")
	 
	 .then()
	 .header("Content-Type", "text/html; charset=ISO-8859-1")
	 .header("Content-Encoding", "gzip")
	 .header("Server", "gws");
	 
 }
	@Test(priority=2)
	void getHeadersData() {
		
		Response res=given()
		
		.when()
		.get("https://www.google.com/");
		
		//Get single Header Data
//		String header=res.getHeader("Content-Type");
//		System.out.println("Content-Type:    " + header);	
		
		//Get all Headers Data
		
		Headers hdval=res.getHeaders();
		
		for(Header hd:hdval) {
			
			System.out.println(hd.getName() + " : " + hd.getValue());
		}
	}

}
