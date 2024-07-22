package lecture3;

import static io.restassured. RestAssured.*;
import org.testng.annotations.Test;

public class QueryAndPathParameters {
	
	@Test
	void QueryAndPathParms() {
		
		given()
		.pathParam("mypath", "users")
		.queryParam("page", 2)
		.queryParam("id", 5)
		
		.when()
		.get("https://reqres.in/api/{mypath}")
		
		.then()
		.statusCode(200)
		.log().all();
	}
}
