package cattleyanadora;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cattleyanadora.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver); 
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//Here all the FindBy (this is for driver.findElement)
	@FindBy(css=".inventory_item")
	List <WebElement> products;
	
	@FindBy(css=".shopping_cart_badge")
	WebElement cartIcon;
	
	@FindBy(id="react-burger-menu-btn")
	WebElement navBar;
	
	@FindBy(css=".bm-item-list")
	WebElement navBarContainer;
	
	@FindBy (id = "logout_sidebar_link")
	WebElement logout;
	
	@FindBy(id="react-burger-cross-btn")
	WebElement closeBar;
	
	@FindBy(css=".bm-item.menu-item")
	List <WebElement> navItems;
	
	@FindBy(css=".inventory_item_desc")
	List <WebElement> desc3;	
	@FindBy(css=".inventory_item_price")
	List <WebElement> productPrice;
	@FindBy(css=".inventory_item_name")
	List <WebElement> productTitle;
	@FindBy(css=".inventory_item_img")
	List <WebElement> productImage;
	

	@FindBy(css=".inventory_details_price")
	private WebElement price;
	@FindBy(css=".inventory_details_name")
	WebElement name;
	@FindBy(css=".inventory_details_desc ")
	WebElement descDetails;
	@FindBy(css=".inventory_details_img")
	WebElement img;
	
	@FindBy(xpath="//button[contains(text(),'Add to cart') or contains(text(),'Remove')]")
	WebElement button;
	
	@FindBy(xpath="//button[contains(text(),'Add to cart') or contains(text(),'Remove')]")
	List <WebElement> buttons;
	
	@FindBy(css = ".inventory_item_price")
	List<WebElement> prices;

	By item = By.cssSelector(".inventory_item_name");
	By addTocart = By.xpath("//button[contains(text(),'Add to cart') or contains(text(),'Remove')]");
	private By priceLocator = By.cssSelector(".inventory_item_price");
	
	// Locate the dropdown element
	@FindBy(css = ".product_sort_container")
	private WebElement sortDropdown;

	// Locate all options inside the dropdown
	@FindBy(css = ".product_sort_container option")
	private List<WebElement> sortOptions;

	
	public WebElement getProductByName(String productName) throws InterruptedException {
		
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName)).findFirst().orElse(null);
		
	    return prod;
	}
	
	
    public String getButtonText() {
		
	    WebElement button = driver.findElement(addTocart);
	    String buttonText = button.getText().trim();
	    
	    return buttonText;    
	    
	}
     
     public void PrintFeaturesDisplay(int count) throws InterruptedException {
    	List<WebElement> descrip = desc3;
    	List<WebElement> priceName = productPrice;
    	List<WebElement> titleName = productTitle;
    	List<WebElement> prodImg = productImage;
    	List<WebElement> Cartbutton = buttons;
 	    
    	int detailsCount = Math.min(count, Math.min(descrip.size(), Math.min(priceName.size(), titleName.size())));
        for (int i = 0; i < detailsCount; i++) {
            System.out.println("Title: " + titleName.get(i).getText());
            System.out.println("Description: " + descrip.get(i).getText());
            System.out.println("Price: " + priceName.get(i).getText());

            if (prodImg.get(i).isDisplayed() && Cartbutton.get(i).isDisplayed()) {
                System.out.println("Product Image/button is displayed.");
            } else {
                System.out.println("Product Image/button is NOT displayed.");
            }
        }
 		
 	}
     
    public boolean verifyProductDetails() {
    	return price.isDisplayed() &&
    			name.isDisplayed() &&
    			descDetails.isDisplayed() &&
    			img.isDisplayed() &&
    			button.isDisplayed();
    }
	
	public List<WebElement> getProductList() {
        return products; //gets the product
    }
	
	
	public List<WebElement> NavbarCheck() {
		   navBar.click();
		   waitForWebElementToAppear(navBarContainer);
		   return navItems;
	   
		}
	
	public void goToProductDetailsPage(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(item).click();
		
    }
	
	
	
   public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addTocart).click();
		waitForRemove(addTocart);

	}

   
   public void addTocartDetailsPage() throws InterruptedException {
	    WebElement addToCartButton = driver.findElement(addTocart); 
	    addToCartButton.click();
	    waitForRemove(addTocart);
	}
   

   
   public String CountCheck() {
	  
	    try {
	    	String ItemCount = cartIcon.getText();
	        return ItemCount;
	    } catch (Exception e) {
	        return "0"; 
	    }
	}

   
   public void NavbarFunction() throws InterruptedException {
	   navBar.click();
	   waitForWebElementToAppear(navBarContainer);
	   closeBar.click();
   
	}
   
   public Boolean NavbarFunctionBoolean() throws InterruptedException {
	   waitForElementToDisappear(navBarContainer);
	   return navBarContainer.isDisplayed();
   
	}
   
   
	public void logoutPage() {
		navBar.click();
		waitForWebElementToAppear(navBarContainer);
		logout.click();
		
	}
	
	public List<String> getProductNames() {
	    List<String> productNames = new ArrayList<>();

	    for (WebElement product : products) {
	        String productName = product.findElement(By.cssSelector(".inventory_item_name")).getText();
	        productNames.add(productName);
	    }

	    return productNames;
	}
	
	public List<String> getPrices() {
	    List<String> productPrices = new ArrayList<>();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElements(prices));

	    for (WebElement priceElement : prices) { 
	        String priceText = priceElement.getText().replace("$", "").trim();
	        productPrices.add(priceText);
	    }

	    return productPrices;
	}


	public void selectSortingOptionByIndex(int index) {
	    Select dropdown = new Select(sortDropdown);
	    dropdown.selectByIndex(index); 
	}


	
}
