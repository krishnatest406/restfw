package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.UserEndPoints;
import io.restassured.response.Response;
import payloads.UserDetails;
import utilities.ExcelUtility;

public class UserTestsExcel {

	UserDetails ud;
	
	@Test(priority=1,dataProvider="providerMethod1",dataProviderClass=ExcelUtility.class)
	public void createUserMain(String id,String username,String firstname,String secondname,String email,String pw,String ph) {	
		
		ud=new UserDetails();
		
		ud.setId(Integer.parseInt(id));
		ud.setUsername(username);
		ud.setFirstName(firstname);
		ud.setLastName(secondname);
		ud.setPassword(pw);
		ud.setEmail(email);
		ud.setPhone(ph);
		
		Response res=UserEndPoints.createUser(ud);
	//	res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(),200);
		
	}
	
	@Test(priority=2)
	public void getUserMain() {		

		Response res=UserEndPoints.getUser(this.ud.getUsername());
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(),200);
		
	}
	
//	@Test(priority=3)
	public void updateUserMain(String upemail,String upph) {	
		
		ud.setEmail(upemail);
		ud.setPhone(upph);
		
		Response res=UserEndPoints.updateUser(this.ud.getUsername(),ud);
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	
//	@Test(priority=4,dataProvider="providerMethod2",dataProviderClass=ExcelUtility.class)
	public void deleteUserMain(String username) {		
		
		Response res=UserEndPoints.deleteUser(username);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(),200);
		
	}
	
	
	
}
