package cattleyanadora.AbstractComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cattleyanadora.CartPage;

//this will be the greatest parent class
//use this when something has a common header
public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//you can add the headers here (ex.navbar)
	@FindBy(css = "[routerlink*='cart']")
	WebElement carHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	
	@FindBy (css = ".shopping_cart_link")
	WebElement cartButton;
	
	By addTocart = By.xpath("//button[contains(text(),'Add to cart') or contains(text(),'Remove')]");
	

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForRemove(By addToCart) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(addToCart, "Remove"));
	}
	
	public void waitForAdd(By addToCart) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(addToCart, "Add to cart"));
	}
	
	
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void addMultipleItemsToCart(int count) {
	    List<WebElement> buttons = driver.findElements(By.cssSelector(".btn_inventory")); // Select all "Add to Cart" buttons
	    
	    for (int i = 0; i < count && i < buttons.size(); i++) {
	        buttons.get(i).click(); // Click each button
	    }
	}
	
	public void RemoveMultipleItemsToCart(int count) {
	    List<WebElement> buttons = driver.findElements(By.cssSelector(".btn_secondary")); // Select all buttons

	    int clicked = 0;
	    for (WebElement button : buttons) {
	        if (button.getText().equals("Remove")) { // Check if button text is "Remove"
	            button.click(); // Click the button
	            clicked++;
	        }
	        if (clicked == count) { // Stop after clicking the required number
	            break;
	        }
	    }
	}
	
	 public void RemoveProduct() throws InterruptedException {
			
			WebElement addToCartButton = driver.findElement(addTocart); 
		    addToCartButton.click();  

		}



	
	
	public CartPage goToCartPage() {
		cartButton.click();
		CartPage cartPage = new CartPage (driver);
		return cartPage;
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(1000);		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		
		//you can ask the dev for the locator of the loading screen
		//visibility- until it appeared
		//invinsibility- until it disappears 
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void screenshot() throws IOException {
		
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\Screenshot_" + timestamp + ".png";

		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(screenshotPath));

	}
}
