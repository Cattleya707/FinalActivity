package cattleyanadora;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cattleyanadora.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css=".complete-header")
	private WebElement message;
	
	@FindBy (id="back-to-products")
	private WebElement backButton;
	
	private By messageLocator = By.cssSelector(".complete-header"); 
	
	public String confirmText() {
		//CheckoutPage cp = new CheckoutPage(driver);
		//cp.submit.click(); do call web elements here. they should only be call internally
		waitForElementToAppear(messageLocator);
		return message.getText();
		
	}
	
	public Boolean BackHomeButton() {
		return backButton.isDisplayed();
		
	}
	
	public ProductCatalog goBacktoProducts() {
		backButton.click();
		ProductCatalog productCatalog = new ProductCatalog (driver);
		return productCatalog;
	}
	

}
