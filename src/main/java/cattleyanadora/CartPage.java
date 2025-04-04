package cattleyanadora;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cattleyanadora.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy (id = "continue-shopping")
	WebElement continueButton;
	
	@FindBy (id = "checkout")
	WebElement checkoutButton;
	
	@FindBy (css = ".cart_quantity")
	WebElement quantity;
	
	@FindBy (css = ".inventory_item_desc")
	WebElement cartItemDesc;

	@FindBy (css = ".inventory_item_name")
	private List<WebElement> cartItems;
	
	@FindBy (css = ".inventory_item_name")
	private WebElement invName;
	
	@FindBy(css = ".inventory_item_price")
	WebElement price;
	
	@FindBy(xpath="//button[contains(text(),'Add to cart') or contains(text(),'Remove')]")
	WebElement button;
	
	private By itemLocator = By.cssSelector(".inventory_item_name");
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	public String confirmProductName() {
		waitForElementToAppear(itemLocator);
		return invName.getText();
		
	}
	
	
	public void VerifyMultipleItems(int count) {
		List<WebElement> items = cartItems;
		int detailsCount = Math.min(count, items.size());
        for (int i = 0; i < detailsCount; i++) {
            System.out.println("Products: " + items.get(i).getText());
	   }
	}
	
	
	public Boolean CartFeaturesDisplay () {
		return quantity.isDisplayed() &&
			   cartItemDesc.isDisplayed() &&
				continueButton.isDisplayed() &&
				checkoutButton.isDisplayed();
	}
	
	
	public ProductCatalog continueShopping() {
		continueButton.click();
		ProductCatalog productCatalog = new ProductCatalog (driver);
		return productCatalog;
		
	}
	
	public CheckoutPage goToCheckOut() {
		checkoutButton.click();
		CheckoutPage checkoutPage = new CheckoutPage (driver);
		return checkoutPage;
		
	}
	
	public List<WebElement> getCartItems() {
        return cartItems;
    }
	
	public boolean verifyProductDetailsInCart(String productName) {
		return  quantity.isDisplayed() &&
				   cartItemDesc.isDisplayed() &&
					price.isDisplayed() &&
					button.isDisplayed() &&
					invName.isDisplayed();
		}
		
	}
	
	
	


