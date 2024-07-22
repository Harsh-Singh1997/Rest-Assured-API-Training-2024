package lecture3;

import static io.restassured. RestAssured.*;
import static io.restassured.matcher. RestAssuredMatchers.*;
import static org.hamcrest. Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesResponseDemo {

	@Test(priority=1)
	void testCookies() {
		
		given()
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.cookie("AEC", "AVYB7cpAAKQjY1zsBKje6IRHVw1RvbGVlA49F_2Oxx9wOtv34t64FL2VNA")  //Testing if cookie data matches
		.log().all();                                                                 //(Note: Cookie data is not static and will change. SO test will fail)
		
	}
	
	@Test(priority=2)
	void getCookieData() {
		
		Response res=given()
		
		.when()
		.get("https://www.google.com/");
		
		//Get single cookie data
//		String cookie=res.cookie("AEC");
//		System.out.println("ACE cookie data =====>" + cookie);
		
		//Get all cookies data
		Map <String, String> K=res.getCookies();
		
		for(String c: K.keySet()) {
			
			String cookie_value = res.getCookie(c);
			System.out.println(c + ": " + cookie_value);
		}
		
	}
}
