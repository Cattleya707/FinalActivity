package cattleyanadora;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cattleyanadora.AbstractComponents.AbstractComponent;

public class Checkout2 extends AbstractComponent {

WebDriver driver;
	
	public Checkout2 (WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css= ".cart_item")
	WebElement productList;
	
	@FindBy (css= ".summary_value_label")
	List <WebElement> payAndship;
	
	@FindBy (css= ".summary_subtotal_label")
	WebElement priceTotal;
	
	@FindBy (css= ".summary_tax_label")
	WebElement tax;
	
	@FindBy (css= ".summary_total_label")
	WebElement overallTotal;
	
	@FindBy (id= "cancel")
	WebElement cancel;
	
	@FindBy (css= ".inventory_item_name")
	WebElement itemName;
	
	@FindBy (css= ".cart_quantity")
	WebElement quantity;
	
	@FindBy (css= ".inventory_item_desc")
	WebElement desc;
	
	@FindBy (css= ".inventory_item_price")
	WebElement price;
	
	
	@FindBy (id= "finish")
	WebElement finish;
	
	public Boolean VerifyCheckoutDetails() {
		return productList.isDisplayed() &&
			priceTotal.isDisplayed() &&
			tax.isDisplayed() &&
			overallTotal.isDisplayed() &&
			cancel.isDisplayed() &&
			finish.isDisplayed()
			;
	}
	
	public List<WebElement> getpayAndshipInfo() {
        return payAndship; 
    }
	
	public void gobacktoProducts() {
		cancel.click();
	}
	
	public double getPriceTotal() {
		return Double.parseDouble(priceTotal.getText().replace("Item total: ", "").replace("$", "").trim());
	}
	
	public double getTaxTotal() {
		return Double.parseDouble(tax.getText().replace("Tax: ", "").replace("$", "").trim());
	}
	
	public String calculateTotal() {
	    return String.valueOf(getPriceTotal() + getTaxTotal());
	}
	
	public String ExpectedTotal() {
		return String.valueOf(overallTotal.getText().replace("Total: ", "").replace("$", "").trim());
	}
	
	public ConfirmationPage goToConfirmPage() {
		finish.click();
		ConfirmationPage confirmationPage = new ConfirmationPage (driver);
		return confirmationPage;
	}
	
	public Boolean VerifyOverallProductDetails() {
		return itemName.isDisplayed() &&
				quantity.isDisplayed() &&
				price.isDisplayed() &&
				desc.isDisplayed();
	}



}
