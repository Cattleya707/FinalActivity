package cattleyanadora;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cattleyanadora.TestComponents.BaseTest;
import cattleyanadora.data.dataDriven;

public class OrderComplete extends BaseTest{
	
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
	public void FinishOrder() throws InterruptedException {
		//Verify if the user is able to click the 'Finish' button and proceed to the checkout complete page
		//Verify if the user is able to proceed to the checkout complete page successfully.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		Checkout2 checkout2 = checkoutPage.CheckoutForm(firstname, lastname, zipcode);
		checkout2.goToConfirmPage();
		String currentUrl= driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/checkout-complete.html");
	}
	
	@Test
	public void GobackToProductPage() throws InterruptedException {
		//Verify if the page navigates back to the product listing after clicking the 'Back Home' button.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		Checkout2 checkout2 = checkoutPage.CheckoutForm(firstname, lastname, zipcode);
		ConfirmationPage confirmationPage = checkout2.goToConfirmPage();
		confirmationPage.goBacktoProducts();
		String currentUrl= driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
	}
	
	@Test
	public void GetConfirmationMessage() throws InterruptedException {
		//Verify if all the required details are displayed on the checkout complete page (Thank you message and 'Back Home' button)
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		Checkout2 checkout2 = checkoutPage.CheckoutForm(firstname, lastname, zipcode);
		ConfirmationPage confirmationPage = checkout2.goToConfirmPage();
		/*System.out.println("The confirmation message is:"+ " " + confirmationPage.confirmText() + " " +
		"and is the back home button displayed?:"+ " "+confirmationPage.BackHomeButton());*/
		Assert.assertEquals(confirmationPage.confirmText(), "Thank you for your order!");
		Assert.assertTrue(confirmationPage.BackHomeButton());
		
	}

}
