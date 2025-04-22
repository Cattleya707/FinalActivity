package cattleyanadora;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cattleyanadora.TestComponents.BaseTest;
import cattleyanadora.data.dataDriven;

public class CheckoutOverview extends BaseTest {
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
	public void CheckoutOverviewPage() throws InterruptedException, IOException {
		//Verify if the user is able to access the checkout overview page.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		Checkout2 checkout2 = checkoutPage.CheckoutForm(firstname, lastname, zipcode);
		String currentUrl= driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/checkout-step-two.html");
		checkout2.screenshot();
			
	}
	
	@Test
	public void CheckoutDetails() throws InterruptedException {
		//Verify if all the required details are displayed on the checkout overview page (Product list, Payment Information, Shipping Information, Price Total, Total, 'Cancel' button, and 'Finish' button).
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		Checkout2 checkout2 = checkoutPage.CheckoutForm(firstname, lastname, zipcode);
		Assert.assertTrue(checkout2.VerifyCheckoutDetails());
		List<WebElement> info = checkout2.getpayAndshipInfo();
		Assert.assertEquals(info.size(),2);	
	}
	
	@Test
	public void BacktoProductPage() throws InterruptedException {
		//Verify if the page redirects to the product listing after clicking the 'Cancel' button.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		Checkout2 checkout2 = checkoutPage.CheckoutForm(firstname, lastname, zipcode);
		checkout2.gobacktoProducts();
		String currentUrl= driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
	}
	
	@Test
	public void VerifyOverviewProductDetails() throws InterruptedException {
		//Verify if all the required product details are displayed on the cart page (Quantity, Product title, Image, Description, Price).
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		Checkout2 checkout2 = checkoutPage.CheckoutForm(firstname, lastname, zipcode);
		Assert.assertTrue(checkout2.VerifyOverallProductDetails());
	}
	
	@Test
	public void VerifyTotal() throws InterruptedException {
		//Verify if the total price calculation for the items is correct.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		Checkout2 checkout2 = checkoutPage.CheckoutForm(firstname, lastname, zipcode);
		Assert.assertEquals(checkout2.ExpectedTotal(), checkout2.calculateTotal());
	}	

}
