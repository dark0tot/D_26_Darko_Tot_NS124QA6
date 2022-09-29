package zadatak.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import zadatak.pages.*;

import java.time.Duration;

public class SwagLabsTest {
  private WebDriver driver;
  private WebDriverWait driverWait;
  private LoginPage loginPage;
  private ProductsPage productsPage;
  private YourCartPage yourCartPage;
  private FormPage formPage;
  private OverviewPage overviewPage;
  private CheckoutCompletePage checkoutCompletePage;

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\dark0\\IdeaProjects\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("https://www.saucedemo.com");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    loginPage = new LoginPage(driver, driverWait);
    productsPage = new ProductsPage(driver, driverWait);
    yourCartPage = new YourCartPage(driver, driverWait);
    formPage = new FormPage(driver, driverWait);
    overviewPage = new OverviewPage(driver, driverWait);
    checkoutCompletePage = new CheckoutCompletePage(driver, driverWait);
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }

  @Test (priority = 1)
  public void loginTest(){
    Assert.assertTrue(loginPage.isLoginBtnPresented());

    loginPage.login("standard_user","secret_sauce");
    Assert.assertTrue(productsPage.isProductsPresented());
  }

  @Test (priority = 2)
  public void addProductTest(){
    Assert.assertTrue(productsPage.isAddToCartBtnPresented());

    productsPage.addProductToCart();

    Assert.assertTrue(productsPage.isRemoveBtnPresented());
    Assert.assertTrue(productsPage.isCartWithProductPresented());

    productsPage.goToCart();
  }

  @Test (priority = 3)
  public void checkoutProductTest() {
    Assert.assertTrue(yourCartPage.isGetCartItemPresented());
    Assert.assertTrue(yourCartPage.isRemoveButtonPresented());
    Assert.assertTrue(yourCartPage.isContinueShoppingBtnPresented());
    Assert.assertTrue(yourCartPage.isCheckoutBtnPresented());
    yourCartPage.checkout();

    Assert.assertTrue(formPage.isGetFormPresented());
  }

  @Test (priority = 4)
  public void informationFormTest() {
    Assert.assertTrue(formPage.isCancelBtnPresented());
    Assert.assertTrue(formPage.isContinueBtnPresented());
    formPage.formCheckout("Andrija", "Zivkovic", "21000");

    String expectedResult = "Total: $32.39";
    String actualResult = overviewPage.getTotal().getText();

    Assert.assertEquals(expectedResult,actualResult);

    overviewPage.finishOrder();
  }

  @Test (priority = 5)
  public void completePageTest() {
    Assert.assertTrue(checkoutCompletePage.isCompletedMessagePresented());
    checkoutCompletePage.backToHomePage();

    Assert.assertTrue(productsPage.isProductsPresented());
  }

  @Test (priority = 6)
  public void logoutTest() {
    productsPage.logout();
  }

  @Test (priority = 7)
  public void cartWithoutLogin() {
    driver.navigate().to("https://www.saucedemo.com/cart.html ");

    Assert.assertTrue(loginPage.isLoginFormPresented());
  }
}
