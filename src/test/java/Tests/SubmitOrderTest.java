package Tests;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		
		
		// Login to webpage
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		// Adding Item to Cart
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//Validate product in Cart
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		
		//Check out page
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		//Thank you page
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		// Quit browser
		Thread.sleep(3000);
	}
	
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("sayan@mail.com", "Pass@1234");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	
//	@DataProvider
//	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "sayan@mail.com");
//		map.put("password", "Pass@1234");
//		map.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "sayan@mail.com");
//		map1.put("password", "Pass@1234");
//		map1.put("product", "IPHONE 13 PRO");
//		return new Object[][] {{map}, {map1}}; 
//	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
