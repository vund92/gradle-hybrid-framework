package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_Cookie;
import com.nopcommerce.common.Common_01_Register_End_User;

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
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterClass;

public class Level_16_Share_Data_C extends BaseTest{
 
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage; 
	String firstName, lastName, emailAddress, validPassword;

	@Parameters("browser")     
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		emailAddress = Common_01_Register_End_User.emailAddress;
		validPassword = Common_01_Register_End_User.password;
		
		log.info("Pre-condition - Step 01: Navigate to Login page"); 
		loginPage = homePage.openLoginPage();
		
		log.info("Pre-condition - Step 2: Set cookie and reload page");
		loginPage.setCookies(Common_01_Register_Cookie.loggedCookies);
		
		for(Cookie cookie : Common_01_Register_Cookie.loggedCookies) {
			System.out.println("Cookie at C Class" + cookie);
		}
		
		loginPage.refreshCurrentPage();
		
//		log.info("Pre-condition - Step 02: Enter to Email textbox with value is '" + emailAddress + "'"); 
//		loginPage.inputToEmailTextbox(emailAddress);
//		
//		log.info("Pre-condition - Step 03: Enter to Password textbox with value is '" + validPassword + "'");
//		loginPage.inputToPasswordTextbox(validPassword);
//		
//		log.info("Pre-condition - Step 04: Click to Login button"); 
//		homePage = loginPage.clickToLoginButton();
		
		log.info("Pre-condition - Step 05: Verify 'My Account' link is displayed"); 
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}
  
	@Test
	public void Search_01_Empty_Data() {
	}
	
	@Test
	public void Search_02_Relative_Product_Name() {
	}
	
	@Test
	public void Search_03_Absolute_Product_Name() {
	}
	
	@Test
	public void Search_04_Parent_Category() {
	}
	
	@Test
	public void Search_05_Incorrect_Manufactorer() {
	}
	
	@Test
	public void Search_06_Correct_Manufactorer() {
	}

  @AfterClass
  public void afterClass() {
	  //driver.quit();
  }
  
  public int generateFakeNumber() {
	  Random rand = new Random();
	  return rand.nextInt(9999);
  }
}
