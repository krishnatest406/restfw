package endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.UserDetails;

public class UserEndPointsPropertyFile {  
	
	 static ResourceBundle getUrls() {
		//get all url from property file
		ResourceBundle rb=ResourceBundle.getBundle("userapi");
		return rb;
	}
	
	
	public static Response createUser(UserDetails payload) {
		
		String post_url=getUrls().getString("post_url");
		
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
		 	.post(post_url);
		
		return res;
		
	}
	
	public static Response getUser(String username) {
		
		String get_url=getUrls().getString("get_url");
		
		Response res=given()
			.pathParam("username",username)
		
		.when()
		 	.get(get_url);
		
		return res;
		
	}
	
	public static Response updateUser(String username,UserDetails payload) {
		
		String update_url=getUrls().getString("update_url");
		
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		
		.when()
		 	.put(update_url);
		
		return res;
		
	}
	
	public static Response deleteUser(String username) {
		
		String delete_url=getUrls().getString("delete_url");
		
		Response res=given()
			.pathParam("username", username)
		
		.when()
		 	.delete(delete_url);
		
		return res;
		
	}

}
