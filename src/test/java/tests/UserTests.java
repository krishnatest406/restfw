package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.UserEndPoints;
import io.restassured.response.Response;
import payloads.UserDetails;
import utilities.ExtentRepoListners;

@Listeners(ExtentRepoListners.class)
public class UserTests {

	Faker f;
	UserDetails ud;
	
	@BeforeClass
	public void setUp() {
		
		f=new Faker();
		
		ud=new UserDetails();
		
		ud.setId(f.idNumber().hashCode());
		ud.setUsername(f.name().username());
		ud.setFirstName(f.name().firstName());
		ud.setLastName(f.name().lastName());
		ud.setPassword(f.internet().password());
		ud.setEmail(f.internet().safeEmailAddress());
		ud.setPhone(f.phoneNumber().cellPhone());
		
	}
	
	@Test(priority=1)
	public void createUserMain() {		
		
		Response res=UserEndPoints.createUser(ud);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(),200);
		
	}
	@Test(priority=2)
	public void getUserMain() {		
		Response res=UserEndPoints.getUser(this.ud.getUsername());
		res.then().log().all();
	
		Assert.assertEquals(res.getStatusCode(),200);
	}
	
	@Test(priority=3)
	public void updateUserMain() {	
		
		ud.setEmail(f.internet().safeEmailAddress());
		ud.setPhone(f.phoneNumber().cellPhone());
		
		Response res=UserEndPoints.updateUser(this.ud.getUsername(),ud);
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	
	@Test(priority=4)
	public void deleteUserMain() {		
		
		Response res=UserEndPoints.deleteUser(this.ud.getUsername());
		res.then().log().all();
	
		Assert.assertEquals(res.getStatusCode(),200);
		
	}
	
	
	
}
