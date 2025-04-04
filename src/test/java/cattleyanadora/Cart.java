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

public class Cart extends BaseTest {
	
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
	public void ItemCount() throws InterruptedException  {
		//Verify if the cart count updates when the user adds one item.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addMultipleItemsToCart(1);
	    System.out.println("Cart Count:"+ " " + productCatalog.CountCheck());
	}
	
	@Test
	public void MultipleCartCounter() throws IOException  {
		//Verify if the cart count updates when the user adds multiple items.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.addMultipleItemsToCart(3);
		System.out.println("Cart Count:"+ " " + productCatalog.CountCheck());

	}
	
	@Test
	public void NavBarItems() {
		//Verify if the hamburger menu button is displayed and if all menu options are visible.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
	    List<WebElement> navList = productCatalog.NavbarCheck();
	    Assert.assertEquals(navList.size(), 4);
	}
	
	@Test
	public void NavBarCloseTest() throws IOException, InterruptedException {
		//Verify if the sidebar closes after clicking the 'X' button.
		ProductCatalog productCatalog =landingPage.loginApplication(username,password);
		productCatalog.NavbarFunction(); 
		System.out.println("The sidebar should be false when it closes:"+productCatalog.NavbarFunctionBoolean());
		
	}
	
	

}
