import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.time.Duration;
import java.util.List;


public class LoginPageWeb {
    WebDriver driver;
    By emailField = By.xpath("//*[@id=\"login-page\"]/div[1]/div[2]/div/div[1]/form/input");
    By passwordField = By.xpath("//*[@id=\"login-page\"]/div[1]/div[2]/div/div[1]/form/div[1]/input");
    By forgotPassBtn = By.xpath("//*[@id=\"login-page\"]/div[1]/div[2]/div/div[1]/form/div[2]");
    By loginBtn = By.xpath("//*[@id=\"login-page\"]/div[1]/div[2]/div/div[1]/form/button");
    By loginGoogle = By.xpath("//*[@id=\"login-page\"]/div[1]/div[2]/div/div[1]/p[2]");
    By connectWalletBtn = By.xpath("//*[@id=\"login-page\"]/div[1]/div[2]/div/div[1]/p[3]");
    By registerBtn = By.xpath("//*[@id=\"login-page\"]/div[1]/div[2]/div/div[1]/p[4]/strong");

    LoginPageWeb(WebDriver driver) {
        this.driver = driver;
    }

    public void waitPageLoaded() throws NoSuchElementException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(emailField));
    }

    public void inputEmail(String email) {
        this.clearEmailField();
        driver.findElement(emailField).sendKeys(email);
    }

    public void clearEmailField() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Sistem operasi adalah Windows
            driver.findElement(emailField).sendKeys(Keys.CONTROL+"a");
            driver.findElement(emailField).sendKeys(Keys.DELETE);
        } else if (os.contains("mac")) {
            // Sistem operasi adalah macOS
            driver.findElement(emailField).sendKeys(Keys.COMMAND+"a");
            driver.findElement(emailField).sendKeys(Keys.DELETE);
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            // Sistem operasi adalah Unix/Linux
        }
    }

    public void inputPassword(String password) {
        this.clearPasswordField();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clearPasswordField() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Sistem operasi adalah Windows
            driver.findElement(passwordField).sendKeys(Keys.CONTROL+"a");
            driver.findElement(passwordField).sendKeys(Keys.DELETE);
        } else if (os.contains("mac")) {
            // Sistem operasi adalah macOS
            driver.findElement(passwordField).sendKeys(Keys.COMMAND+"a");
            driver.findElement(passwordField).sendKeys(Keys.DELETE);
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            // Sistem operasi adalah Unix/Linux
        }
    }

    public void clickLoginButton() {
        WebElement loginbutton = driver.findElement(loginBtn);
        if(loginbutton.isEnabled()) {
            loginbutton.click();
        }
    }

    public String getErrorMessage() {
        By errorMsg = By.className("text-rose-700");
        List<WebElement> errorMessage = driver.findElements(errorMsg);
        if(errorMessage.size() > 0) {
            return errorMessage.get(0).getText();
        }
        return "";
    }

}
