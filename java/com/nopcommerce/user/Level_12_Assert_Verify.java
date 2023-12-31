package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import org.testng.annotations.AfterClass;

public class Level_12_Assert_Verify extends BaseTest{
 
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage; 
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;
	String firstName, lastName, emailAddress, password;

	@Parameters("browser")     
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		password = "123456";

	}
  
	@Test
	public void User_01_Register_Login() {
		registerPage = homePage.openRegisterPage();
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");
		
		//System.out.println("Register Page - Step 05: Click to Logout link");
		//homePage = registerPage.clickToLogoutLink();

		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		
		homePage = loginPage.clickToLoginButton();
		verifyFalse(homePage.isMyAccountLinkDisplayed());
		
		customerInforPage = homePage.openMyAccountPage();
		verifyFalse(customerInforPage.isCustomerInforPageDisplayed());
		
	}
	
	//@Test
	public void User_02_Switch_Page() {
		// Customer Infor -> Address
		addressPage = customerInforPage.openAddressPage(driver);
		// Address -> My Product Review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		// My Product Review -> Reward Point
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
		// Reward Point -> Address
		addressPage = rewardPointPage.openAddressPage(driver);
		// Address -> Reward Point
		rewardPointPage = addressPage.openRewardPointPage(driver);
		// Reward Point -> My Product Review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
	}
	
	//@Test
	public void User_03_Dynamic_Page_01() {
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPagesAtMyAccountByName("Reward points");
		// Reward Point -> Address
		addressPage = (UserAddressPageObject) rewardPointPage.openPagesAtMyAccountByName("Addresses");
		// Address -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) addressPage.openPagesAtMyAccountByName("Reward points");
		// Reward Point -> My Product Review
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPagesAtMyAccountByName("My product reviews");
		//My Product Review -> Customer info
		customerInforPage = (UserCustomerInforPageObject) myProductReviewPage.openPagesAtMyAccountByName("Customer info");
	}
	
	//@Test
	public void User_03_Dynamic_Page_02() {
		// Customer info -> My Product Review
		customerInforPage.openPagesAtMyAccountByPageName("My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
		// My Product Review -> Reward Point
		myProductReviewPage.openPagesAtMyAccountByPageName("Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		// Reward Point -> Address
		rewardPointPage.openPagesAtMyAccountByPageName("Addresses");
		addressPage = PageGeneratorManager.getUserAddressPage(driver);
		// Address -> Reward Point
		addressPage.openPagesAtMyAccountByPageName("Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
	}

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public int generateFakeNumber() {
	  Random rand = new Random();
	  return rand.nextInt(9999);
  }
}
