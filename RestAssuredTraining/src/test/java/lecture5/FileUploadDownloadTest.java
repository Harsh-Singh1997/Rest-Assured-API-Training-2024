package lecture5;

import static io.restassured. RestAssured.*;
import static io.restassured.matcher. RestAssuredMatchers.*;
import static org.hamcrest. Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadDownloadTest {
	
	@Test
	void singleFileUpload() {
		
		File myfile = new File("G:\\Test files\\Test1.txt");
		
		given()
		.multiPart("file", myfile)
		.contentType("multipart/form-data")
		
		.when()
		.post("http://localhost:8080/uploadFile")
		
		.then()
		.statusCode(200)
		.body("fileName", equalTo("Test1.txt"))
		.log().all();
		
	}
	
	@Test
	void multipleFileUpload() {
		
		File myfile = new File("G:\\Test files\\Test1.txt");
		File myfile2 = new File("G:\\Test files\\Test2.txt");
		
		given()
		.multiPart("files", myfile)
		.multiPart("files", myfile2)
		.contentType("multipart/form-data")
		
		.when()
		.post("http://localhost:8080/uploadMultipleFiles")
		
		.then()
		.statusCode(200)
		.body("[0].fileName",equalTo("Test1.txt"))
		.body("[1].fileName",equalTo("Test2.txt"))
		.header("Content-Type","application/json")
		.log().all();
	}
	
	@Test
	void downloadFile() {
		
		given()
		
		.when()
		.get("http://localhost:8080/downloadFile/Test1.txt")
		
		.then()
		.statusCode(200)
		.log().body();
	}

}
