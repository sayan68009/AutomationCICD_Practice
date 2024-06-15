package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	@FindBy(css=".cartSection h3")
	List <WebElement> cartProducts;
	
	
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkOutEle.click();
		return new CheckoutPage(driver);
	}

	private Object CheckoutPage(WebDriver driver2) {
		// TODO Auto-generated method stub
		return null;
	}

}
