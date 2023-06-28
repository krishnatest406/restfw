package endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.UserDetails;

public class UserEndPoints {  
	
	
	public static Response createUser(UserDetails payload) {
		
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
		 	.post(Routes.post_url);
		
		return res;
		
	}
	
	public static Response getUser(String username) {
		
		Response res=given()
			.pathParam("username",username)
		
		.when()
		 	.get(Routes.get_url);
		
		return res;
		
	}
	
	public static Response updateUser(String username,UserDetails payload) {
		
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		
		.when()
		 	.put(Routes.update_url);
		
		return res;
		
	}
	
	public static Response deleteUser(String username) {
		
		Response res=given()
			.pathParam("username", username)
		
		.when()
		 	.delete(Routes.get_url);
		
		return res;
		
	}

}
