package cattleyanadora;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cattleyanadora.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css= ".title")
	WebElement Header;
	
	@FindBy (id= "first-name")
	WebElement firstName;
	
	@FindBy (id= "last-name")
	WebElement lastName;
	
	@FindBy (id= "postal-code")
	WebElement postalCode;
	
	@FindBy (id= "cancel")
	WebElement cancelButton;
	
	@FindBy (id= "continue")
	WebElement continueButton;

	
	public Boolean VerifyCheckoutDetails() {
		return Header.isDisplayed() &&
			firstName.isDisplayed() &&
			lastName.isDisplayed() &&
			postalCode.isDisplayed() &&
			cancelButton.isDisplayed() &&
			continueButton.isDisplayed()
			;
	}
	
	public Checkout2 CheckoutForm(String firstname, String lastname, String postalcode) {
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		postalCode.sendKeys(postalcode);;
		continueButton.click();
		Checkout2 checkout2 = new Checkout2(driver);
		return checkout2;
	}
	
	
	
	public CartPage goBacktoCartPage() {
		cancelButton.click();
		CartPage cartPage = new CartPage (driver);
		return cartPage;
	}

}
