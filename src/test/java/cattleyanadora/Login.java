package cattleyanadora;

import java.io.IOException;
import cattleyanadora.data.dataDriven; 
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import cattleyanadora.TestComponents.BaseTest;

public class Login extends BaseTest{
	
	private dataDriven d;
	private ArrayList<ArrayList<String>> userData;
	
	@BeforeClass
	public void setup() throws IOException {
	    // Initialize dataDriven and fetch data only once
	    d = new dataDriven();
	    userData = d.getData();
	}
	
	@Test
	public void LoginLanding() throws IOException {
		//Verify if the user is able to access the login page and if all the required fields are displayed.
		LandingPage landingPage = launchApplication();
		String currentUrl= driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/");
		landingPage.screenshot();
	}
	
	@Test
	public void IncorrectUsername() throws IOException {
		//Verify that the user is unable to log in using an incorrect username.
        String username = userData.get(2).get(0); 
        String password = userData.get(2).get(1); 
		landingPage.loginApplication(username,password);
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",landingPage.getErrorMessage());
	}
	
	@Test
	public void IncorrectPassword() throws IOException {
		//Verify that the user is unable to log in using an incorrect password.
        String username = userData.get(3).get(0); 
        String password = userData.get(3).get(1); 
        landingPage.loginApplication(username,password);
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",landingPage.getErrorMessage());
	}
	
	@Test
	public void BlankUsername() throws IOException {
		//Verify that the user is unable to log in when the username field is left blank.
		String password = userData.get(0).get(1); 
		landingPage.loginApplication("",password);
		Assert.assertEquals("Epic sadface: Username is required",landingPage.getErrorMessage());
	}
	
	@Test
	public void BlankPassword() throws IOException, InterruptedException {
		//Verify that the user is unable to log in when the password field is left blank.
		String username = userData.get(0).get(0); 
		landingPage.loginApplication(username,"");
		Assert.assertEquals("Epic sadface: Password is required",landingPage.getErrorMessage());
	}
	@Test
	public void BlankFields() throws IOException {
		//Verify that the user is unable to log in when both the username and password fields are left blank.
		landingPage.loginApplication("","");
		Assert.assertEquals("Epic sadface: Username is required",landingPage.getErrorMessage());
	}
	
	@Test
	public void IncorrectLogin() throws IOException {
		//Verify that the user is unable to log in when the account is locked.
		String username = userData.get(1).get(0);
		String password = userData.get(1).get(1); 
		landingPage.loginApplication(username,password);
		Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.",landingPage.getErrorMessage());
	}

	@Test
	public void LoginSucess() throws IOException {
		//Verify that a standard user is able to log in successfully.
		String username = userData.get(0).get(0);
		String password = userData.get(0).get(1); 
		landingPage.loginApplication(username,password);


	}
	

	
	
}
