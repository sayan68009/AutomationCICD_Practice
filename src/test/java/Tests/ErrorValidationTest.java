package Tests;

import pageObjects.CartPage;
import pageObjects.ProductCatalogue;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer = TestComponents.Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		landingPage.loginApplication("sayan@mail.com", "Pas@12");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		
		String productName = "ADIDAS ORIGINAL";
		
		// Login to webpage
		ProductCatalogue productCatalogue = landingPage.loginApplication("sayan@mail.com", "Pass@1234");

		// Adding Item to Cart
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//Validate product in Cart
		Boolean match = cartPage.verifyProductDisplay("ADIDDAS ORIGINALL");
		Assert.assertFalse(match);
	}

}
