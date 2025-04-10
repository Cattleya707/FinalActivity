package cattleyanadora;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cattleyanadora.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver); //we user super driver bcs to call the greatest parent and so AbstractComponent can also create WebDriver
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(id="user-name")
	WebElement userEmail;
	
	@FindBy(name="password")
	WebElement passwordEle;
	
	@FindBy(className="btn_action")
	WebElement submit;

	@FindBy(css="h3[data-test='error']")
	WebElement errorMessage;
	
	@FindBy(css=".login-box") 
	WebElement loginBox;
	
	//always add a String condition to add an input or verify an input
	public ProductCatalog loginApplication(String email, String password) {
		//use the WebElement to send to the string
		//instead of driver.send keys, we'll use the FindByWebElement
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	
	public void goTo() {
		driver.get("https://www.saucedemo.com/");
	}
}
