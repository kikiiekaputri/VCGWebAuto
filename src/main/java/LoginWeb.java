import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginWeb {
    String messages;
    WebDriver driver;

    public LoginWeb() {
        messages = "";
    }

    private WebDriver openURL() {
        // Locate chromedriver.exe didalam folder /user/Downloads/chromedriver/<chromedriver.exe>
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/Downloads/chromedriver_mac_arm64/chromedriver");
        // Inisialisasi objek WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app.vcgamers.io/");

        return driver;
    }

    private void clickLoginBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement signInButton  = null;

        try {
            // Jika prompt muncul, maka klik background lalu klik sign in button
            //WebElement prompt = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"hsl-00B__031-body\"]/div[1]")));
            WebElement promptBG = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"hsl-00B__031-backdrop\"]")));
            promptBG.click();

            // Setelah prompt hilang, baru cari elemen log in button
            signInButton = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[1]/header/div[3]/div/div/a"));
            signInButton.click();
        } catch (NoSuchElementException e) {
            // Jika prompt tidak muncul, maka langsung klik sign in button
            signInButton = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[1]/header/div[3]/div/div/a"));
            signInButton.click();
        }

        try {
            WebElement pageNotFound = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/div"));
            driver.get(driver.getCurrentUrl());
        } catch (NoSuchElementException e) {
            // Do nothing
        }
    }

    private void fillForm(String emailInput, String passwordInput) {
        // Menunggu page loaded completely sampai semua form muncul (maks 10 detik)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"login-page\"]/div[1]/div[2]/div/div[1]/form/input")));
        email.sendKeys(emailInput);

        WebElement password = driver.findElement(By.xpath("//*[@id=\"login-page\"]/div[1]/div[2]/div/div[1]/form/div[1]/input"));
        password.sendKeys(passwordInput);

        WebElement btnMasuk = driver.findElement(By.xpath("//*[@id=\"login-page\"]/div[1]/div[2]/div/div[1]/form/button"));
        btnMasuk.click();
    }

    private void setMessages(String message) {
        messages = messages + "\n";
    }

    private void loginResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://app.vcgamers.io/")) {
            System.out.println("Login berhasil!");
        } else if((currentUrl.equals("https://auth.vcgamers.io/login/marketplace?%2F"))) {

        }
    }

    public String getMessages() {
        return messages;
    }

    public void doLogin(String email, String password) {
        openURL();
        clickLoginBtn();
        fillForm(email, password);
        loginResult();
    }

    public void tearDown() {
        driver.quit();
    }
}
