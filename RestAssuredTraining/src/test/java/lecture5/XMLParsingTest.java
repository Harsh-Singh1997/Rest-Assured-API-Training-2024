package lecture5;

import static io.restassured. RestAssured.*;
import static io.restassured.matcher. RestAssuredMatchers.*;
import static org.hamcrest. Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLParsingTest {
	
	@Test
	void testXMLResponse() {
		
		//Approach 1
		
		/*
		given()
		
		.when()
		.get("https://thetestrequest.com/authors.xml")
		
		.then()
		.statusCode(200)
		.header("Content-Type", "application/xml; charset=utf-8")
		.body("objects.object[0].name", equalTo("Karl Zboncak"))
		.body("objects.object[1].id", equalTo("2"));
		*/
		
		//Approach 2
		
		Response res=
		given()
		
		.when()
		.get("https://thetestrequest.com/authors.xml");
		
		String author_id = res.xmlPath().get("objects.object[1].id").toString();
		Assert.assertEquals(author_id, "2");
		
		String response_header = res.header("Content-Type");
		Assert.assertEquals(response_header, "application/xml; charset=utf-8");
		
		String author_name = res.xmlPath().get("objects.object[3].name").toString();
		Assert.assertEquals(author_name, "Gracia Keeling");
	}
	
	@Test
	void XMLBodyParsingTest() {
		
		Response res=
		given()
		
		.when()
		.get("https://thetestrequest.com/authors.xml");
		
		//Creating XML object
		XmlPath xmlobj= new XmlPath(res.asString());
		
		List<String> author_allnames=xmlobj.getList("objects.object.name");
		List<String> author_emails=xmlobj.getList("objects.object.email");
		
		//validate size of list
		Assert.assertEquals(author_allnames.size(), 5);
		
		//Print all names of authors
		for(String names: author_allnames) {
			
			System.out.println("Author:" + '\t' + names);
		}
		
		for(String email:author_emails) {
			
			System.out.println("Email:" + '\t' + email );
		}
		
		//Validating name from string
		
		boolean status = false;
		
		for(String authorname:author_allnames) {
			
			if(authorname.equals("Dede Tillman")) {
				status = true;
				break;
			}
		}
		
		Assert.assertTrue(status);
		
	}

}
