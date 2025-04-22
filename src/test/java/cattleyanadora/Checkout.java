package cattleyanadora;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cattleyanadora.TestComponents.BaseTest;
import cattleyanadora.data.dataDriven;

public class Checkout extends BaseTest{
	String productName = "Sauce Labs Backpack";
	private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String zipcode;
    private dataDriven d;
    private ArrayList<ArrayList<String>> userData;
    
	@BeforeClass
	public void setup() throws IOException {
		d = new dataDriven();
        userData = d.getData();		
	    username = userData.get(0).get(0);
		password = userData.get(0).get(1); 
		firstname = userData.get(0).get(2);
		lastname = userData.get(0).get(3);
		zipcode = userData.get(0).get(4);
		
	}
	
	@Test
	public void checkoutPage() throws IOException {
		//Verify if the user is able to access the checkout user information page after clicking the 'Checkout' button.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.screenshot();
		String currentUrl= driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/checkout-step-one.html");
	}
	
	@Test
	public void checkoutPageDetails() throws InterruptedException {
		//Verify if all the required details are displayed on the 'Your Information' page (Header, First Name, Last Name, Zip/Postal Code, 'Cancel' button, and 'Continue' button).
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		Assert.assertTrue(checkoutPage.VerifyCheckoutDetails());
		
	}
	
	@Test
	public void goBacktoCart() throws IOException {
		//Verify if the page redirects to the cart page after clicking the 'Cancel' button.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		checkoutPage.goBacktoCartPage();
		cartPage.screenshot();
		String currentUrl= driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/cart.html");

}
	@Test
	public void AllBlankFields() throws InterruptedException {
		//Verify if the user is unable to proceed when all required fields are blank (including error message verification).
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		checkoutPage.CheckoutForm("", "", "");
		Assert.assertEquals("Error: First Name is required",landingPage.getErrorMessage());
			
	}
	
	@Test
	public void FirstNameBlankField() throws InterruptedException {
		//Verify if the user is unable to proceed when the First Name is blank while other fields are filled.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		checkoutPage.CheckoutForm("", lastname, zipcode);
		Assert.assertEquals("Error: First Name is required",landingPage.getErrorMessage());
			
	}
	
	@Test
	public void LastNameBlankField() throws InterruptedException {
		//Verify if the user is unable to proceed when the Last Name is blank while other fields are filled.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		checkoutPage.CheckoutForm(firstname, "", zipcode);
		Assert.assertEquals("Error: Last Name is required",landingPage.getErrorMessage());
			
	}
	
	@Test
	public void PostalCodeBlankField() throws InterruptedException {
		//Verify if the user is unable to proceed when the Last Name is blank while other fields are filled.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		checkoutPage.CheckoutForm(firstname, lastname, "");
		Assert.assertEquals("Error: Postal Code is required",landingPage.getErrorMessage());
			
	}
	
	@Test
	public void OnlyFirstName() throws InterruptedException {
		//Verify if the user is unable to proceed when only the First Name is entered.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		checkoutPage.CheckoutForm(firstname, "", "");
		Assert.assertEquals("Error: Last Name is required",landingPage.getErrorMessage());
			
	}
	
	@Test
	public void OnlyLastName() throws InterruptedException {
		//Verify if the user is unable to proceed when only the Last Name is entered.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		checkoutPage.CheckoutForm("", lastname, "");
		Assert.assertEquals("Error: First Name is required",landingPage.getErrorMessage());
			
	}
	
	@Test
	public void OnlyPostalCode() throws InterruptedException {
		//Verify if the user is unable to proceed when only the Postal Code is entered.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		checkoutPage.CheckoutForm("", "", zipcode);
		Assert.assertEquals("Error: First Name is required",landingPage.getErrorMessage());
			
	}
	
	@Test
	public void CheckoutInputSuccess() throws InterruptedException {
		//Verify if the user is able to proceed when all required fields are filled.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		checkoutPage.CheckoutForm(firstname, lastname, zipcode);
			
	}
}
