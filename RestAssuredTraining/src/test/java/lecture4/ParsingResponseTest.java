package lecture4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured. RestAssured.*;
import static io.restassured.matcher. RestAssuredMatchers.*;
import static org.hamcrest. Matchers.*;

import org.json.JSONException;
import org.json.JSONObject;

public class ParsingResponseTest {

	@Test(priority=1)
	void testJSONResponse() {
		
		/*
		//Approach 1 to validate
		given()
		.contentType("ContentType.json")
		
		.when()
		.get("http://localhost:3000/store")
		
		

		.then()
		.statusCode(200)
		.header("Content-Type", "application/json")
		.body("book[3].title",equalTo("The Lord of the Rings"));
		*/
		
		//Approach 2 to validate
		
		Response res = given()
		.contentType(ContentType.JSON)
		
		.when()
		.get("http://localhost:3000/store");
		
		// Checking status code using response and assertion
		Assert.assertEquals(res.statusCode(), 200);
		
		// checking header
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		//checking some value in body of json
		String title = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(title, "The Lord of the Rings");
	}
	
	@Test(priority=2)
	void testJSONBodyData() throws JSONException {
		
		Response res = given()
		.contentType(ContentType.JSON)
		
		.when()
		.get("http://localhost:3000/store");
		
		//Creating JSONObject class object
		JSONObject jo = new JSONObject(res.asString());  
		
		//finding all book's title using for JSONArray
		for(int i=0; i<jo.getJSONArray("book").length(); i++) {
			
			String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println("title: " + booktitle);
		}
		
		//Checking if book is available using assertion
		boolean status=false;
		
		for(int j=0; j<jo.getJSONArray("book").length(); j++) {
			
			String Exp_title = jo.getJSONArray("book").getJSONObject(j).get("title").toString();
			
			if(Exp_title.equals("Moby Dick")) {
				
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);	
		
		//finding total price of books in JSON
		
		double totalPrice = 0.0;
		
for(int p=0; p<jo.getJSONArray("book").length(); p++) {
			
			 String price =  jo.getJSONArray("book").getJSONObject(p).get("price").toString();
			 totalPrice = totalPrice + Double.parseDouble(price);
			 
		}

		System.out.println("Total price of all books is " + totalPrice);
		
	}
	
}
