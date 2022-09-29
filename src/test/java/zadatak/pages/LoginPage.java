package zadatak.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
  private By loginForm = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]");
  private By username = By.id("user-name");
  private By password = By.id("password");
  private By loginBtn = By.id("login-button");

  public LoginPage(WebDriver driver, WebDriverWait driverWait) {
    super(driver, driverWait);
  }

  public WebElement getUsername() {
    return getDriver().findElement(username);
  }

  public WebElement getLoginForm() {
    return getDriver().findElement(loginForm);
  }

  public WebElement getPassword() {
    return getDriver().findElement(password);
  }

  public WebElement getLoginBtn() {
    return getDriver().findElement(loginBtn);
  }

  public boolean isLoginBtnPresented(){
    return getLoginBtn().isDisplayed();
  }

  public boolean isLoginFormPresented() {
    return getLoginForm().isDisplayed();
  }

  public void login(String username, String password) {
    getUsername().clear();
    getPassword().clear();
    getUsername().sendKeys(username);
    getPassword().sendKeys(password);
    getLoginBtn().click();
  }
}
