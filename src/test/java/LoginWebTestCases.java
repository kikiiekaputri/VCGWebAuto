import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginWebTestCases {
    static String url = "https://app.vcgamers.io/";
    WebDriver driver;

    public LoginWebTestCases() {
    }

    public void initiateDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/Downloads/chromedriver_mac_arm64/chromedriver");
        // Inisialisasi objek WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public String loginResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://app.vcgamers.io/")) {
            return "Login berhasil!";
        } else if((currentUrl.equals("https://auth.vcgamers.io/login/marketplace?%2F"))) {
            return "";
        }
        return "";
    }

    public void doLogin(String email, String password) {
        initiateDriver();
        HomepageWeb hw = new HomepageWeb(driver);
        hw.closePrompt();
        hw.clickMasukDaftarBtn();

        LoginPageWeb lw = new LoginPageWeb(driver);
        lw.waitPageLoaded();
        lw.inputEmail(email);
        lw.inputPassword(password);
        lw.clickLoginButton();
        System.out.println(lw.getErrorMessage());
        System.out.println(loginResult());

        lw.driver.quit();
    }

    public static void main(String[] args) {
        //locating chromedriver

        LoginWebTestCases logintest = new LoginWebTestCases();

        //case 1 : kosongkan email
        System.out.println("case1");
        logintest.doLogin("","ciwkiciw");

        //case 2 : kosongkan password
        System.out.println("case2");
        logintest.doLogin("buyerb@mailinator.com","");

        //case 3 : login dengan format email tidak valid
        System.out.println("case3");
        logintest.doLogin("buyerb","ciwkiciw");

        //case 4 : login dengan email tidak terdaftar & password valid
        System.out.println("case4");
        logintest.doLogin("buyerbbbb@mailinator.com","ciwkiciw");

        //case 5 : login dengan email terdaftar & password tidak valid
        System.out.println("case5");
        logintest.doLogin("buyerbb@mailinator.com","ciwww");

        //case 6 : login dengan email dan password valid
        System.out.println("case6");
        logintest.doLogin("buyerb@mailinator.com","ciwkiciw");

    }
}
