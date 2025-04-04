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

public class Product extends BaseTest {
	
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
	public void ProductPageDisplayed() throws IOException, InterruptedException {
		//Verify if the user is able to access the product listing page.
		landingPage.loginApplication(username,password);
		String currentUrl= driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
	}
	
	@Test
	public void ProductList() throws IOException, InterruptedException {
		//Verify if all products are displayed.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
	    List<WebElement> products = productCatalog.getProductList();
		Assert.assertEquals(products.size(),6);
		System.out.println(productCatalog.getProductNames());
	}
	
	@Test
	public void CartButtonTest() throws IOException, InterruptedException {
		//Verify if the 'Add to Cart' button is clickable and if the label changes to 'Remove' after clicking the button.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addProductToCart(productName);
	    Assert.assertEquals(productCatalog.getButtonText(), "Remove");	
	    
	}
	@Test
	public void RequiredDetails() throws IOException, InterruptedException {
		//Verify if all the required details are displayed on the product list (Product title, Image, Description, Price, and 'Add to Cart' button).
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		Thread.sleep(3000);
		productCatalog.PrintFeaturesDisplay(3);
		
		
	}
	
	@Test
	public void Ascending() throws IOException {
		//Verify if the product list is sorted correctly by Name (A to Z).
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.selectSortingOptionByIndex(0);
		Assert.assertEquals(productCatalog.getProductNames(), d.getAscendingProductsFromExcel());
		
		
	}
	
	@Test
	public void Descending() throws IOException {
		//Verify if the product list is sorted correctly by Name (Z to A).
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.selectSortingOptionByIndex(1);
		Assert.assertEquals(productCatalog.getProductNames(), d.getDescendingProductsFromExcel());
		
		
	}
	
	@Test
	public void LowToHigh() throws IOException {
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.selectSortingOptionByIndex(2);
		Assert.assertEquals(productCatalog.getPrices(), d.getLowtoHighPriceFromExcel());
		
		
	}
	
	@Test
	public void HighToLow() throws IOException, InterruptedException {
		//Verify if the product list is sorted correctly by Price (High to Low).
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.selectSortingOptionByIndex(3);
		Assert.assertEquals(productCatalog.getPrices(), d.getHightoLowPriceFromExcel());
		
		
	}
	
	
	
}
