package cattleyanadora;

import java.io.IOException;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cattleyanadora.TestComponents.BaseTest;
import cattleyanadora.data.dataDriven;

public class ProductDetails extends BaseTest {
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
	public void ProductDetailPageTest() throws InterruptedException {
		//Verify if the user is able to access the product detail page..
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		productCatalog.goToProductDetailsPage(productName);
		String currentUrl= driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory-item.html?id=4");
	    
	}

	@Test
	public void ProductDetailPageRemove() throws IOException, InterruptedException {
		//Verify if the 'Remove' button is displayed on the product detail page when the product is already in the cart.
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		productCatalog.goToProductDetailsPage(productName);
		productCatalog.addTocartDetailsPage();
	    Assert.assertEquals(productCatalog.getButtonText(), "Remove");	
	    
	}
	
	@Test
	public void RemoveItemUpdate() throws IOException, InterruptedException {
		//Verify if the user is able to click the 'Remove' button and if the cart count updates.
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		productCatalog.goToProductDetailsPage(productName);
		productCatalog.addTocartDetailsPage();
		productCatalog.RemoveProduct();
		System.out.println("The number of cart items left after removed:" + " " + productCatalog.CountCheck());
		   
	}
	
	@Test
	public void AddToCartDetails() throws InterruptedException  {
		//Verify if the 'Add to Cart' button is clickable and if the cart count updates.
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
	    productCatalog.goToProductDetailsPage(productName);
	    productCatalog.addTocartDetailsPage();
	    System.out.println("The number of items in the cart after added:" + " " + productCatalog.CountCheck());
	}
	
	@Test
	public void DetailsPagedesc() throws IOException, InterruptedException {
		//Verify if all the required details are displayed on the product detail page (Image, Product Name, Description, Details, and 'Add to Cart' button)
		ProductCatalog productCatalog =landingPage.loginApplication(username, password);
		productCatalog.goToProductDetailsPage(productName);
	    Assert.assertTrue(productCatalog.verifyProductDetails());
	}
	
}
