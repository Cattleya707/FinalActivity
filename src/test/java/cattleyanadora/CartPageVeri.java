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

public class CartPageVeri extends BaseTest {
	String productName = "Sauce Labs Backpack";
	private String username;
    private String password;
    private dataDriven d;
    private ArrayList<ArrayList<String>> userData;
    
	@BeforeClass
	public void setup() throws IOException {
		d = new dataDriven();
        userData = d.getData();		
	    username = userData.get(0).get(0);
		password = userData.get(0).get(1); 
	}

	@Test
	public void CartPageAcess() throws IOException {
		//Verify if the user is able to access the cart page.
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		CartPage cartPage = productCatalog.goToCartPage();
		String currentUrl= driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/cart.html");
		cartPage.screenshot();
	}
	
	@Test
	public void ContinueShopping() throws IOException {
		//Verify if the page navigates back to the product listing after clicking 'Continue Shopping'.
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		CartPage cartPage = productCatalog.goToCartPage();
		cartPage.continueShopping();
		String currentUrl= driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
		productCatalog.screenshot();

	}
	@Test
	public void CartFeaturesDisplay() throws IOException, InterruptedException {
		//Verify if all the required details are displayed on the cart page (Quantity, Description, 'Continue Shopping' button, and 'Checkout' button).
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		Assert.assertTrue(cartPage.CartFeaturesDisplay());

	}
	
	@Test
	public void ProductDisplay() throws IOException, InterruptedException {
		//Verify if all the required product details are displayed in the cart (Quantity, Product title, Image, Description, Price, and 'Add to Cart' button)
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		Assert.assertTrue(cartPage.verifyProductDetailsInCart(productName));

	}
	@Test
	public void AddedItemDisplay() throws IOException, InterruptedException {
		//Verify if the added product is displayed in the cart.
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		String currentProduct = cartPage.confirmProductName();
		Assert.assertEquals(currentProduct, productName);
		cartPage.screenshot();

	}
	
	@Test
	public void RemoveItemUpdate() throws IOException, InterruptedException {
		//Verify if the user is able to remove an item from the cart and if the cart count updates.
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		productCatalog.addProductToCart(productName);
		productCatalog.goToCartPage();
		productCatalog.RemoveProduct();
		productCatalog.screenshot();
		System.out.println("the number of removed cartItems are"  + " "+ productCatalog.CountCheck());
		   
	}
	
	
	@Test
	public void CartPesistTest() throws IOException, InterruptedException {
		//Verify if items in the cart persist after the user re-logs in.
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		productCatalog.addProductToCart(productName);
		productCatalog.logoutPage();
		
        landingPage.loginApplication(username, password);
        productCatalog.screenshot();
        Thread.sleep(2000);
		System.out.println("If there is a cart count here, that means the icon still persists:" 
				+ " " + productCatalog.CountCheck());
	}
	
	@Test
	public void MultipleCartItemsDisplay() throws IOException, InterruptedException {
		//Verify if multiple products are displayed in the cart.
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		productCatalog.addMultipleItemsToCart(3);
		CartPage cartpage =productCatalog.goToCartPage();
		cartpage.VerifyMultipleItems(3);
		cartpage.screenshot();
	}
	
	@Test
	public void RemoveMultipleCartCounter() throws IOException, InterruptedException {
		//Verify if the user is able to remove all items from the cart and if the cart count updates.
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		productCatalog.addMultipleItemsToCart(3);
		productCatalog.goToCartPage();
		productCatalog.RemoveMultipleItemsToCart(2);;
		productCatalog.screenshot();
		System.out.println("Cart Count:"+ " " + productCatalog.CountCheck());

	}
		
	}
	
	
