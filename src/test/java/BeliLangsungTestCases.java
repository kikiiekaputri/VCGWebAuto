import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BeliLangsungTestCases {
    static String url = "https://app.vcgamers.io/";
    WebDriver driver;

    public BeliLangsungTestCases() {

    }
    public void initiateDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/Downloads/chromedriver_mac_arm64/chromedriver");
        // Inisialisasi objek WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    //beli langsung kilat, ada catatan, qty 2, sudah login, payment method vc coin
    public void beliLangsung1(String email, String password, String sectionName, String catatan, int counter, String[] PIN) throws InterruptedException {
        initiateDriver();
        HomepageWeb hw = new HomepageWeb(driver);
        hw.closePrompt();
        hw.clickMasukDaftarBtn();

        LoginPageWeb lw = new LoginPageWeb(driver);
        lw.waitPageLoaded();
        lw.inputEmail(email);
        lw.inputPassword(password);
        lw.clickLoginButton();

        hw.closePrompt();
        hw.findSection(sectionName);
        hw.clickItem(sectionName);

        DetailProductPage dp = new DetailProductPage(driver);
        dp.closePrompt();
        dp.fillCatatanField(catatan);
        dp.increaseQuantity(counter);
        dp.clickBeliLangsung();

        CheckoutBeliLangsung cbl = new CheckoutBeliLangsung(driver);
        cbl.waitPageLoaded();
        cbl.openPaymentMethodBox();
        boolean status = cbl.isVCCoinEnough();
        cbl.choosePaymentMethod("vc coin");
        cbl.clickBayarButton();
        if(status) {
            cbl.clickLanjutPembayaranButton();
            cbl.inputPIN(PIN);
            cbl.validatePIN(PIN);
        }

        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {

        BeliLangsungTestCases bltc = new BeliLangsungTestCases();
        //case1
        System.out.println("case 1: beli langsung kilat, ada catatan, qty 2, sudah login, payment method vc coin");
        String email = "buyerb@mailinator.com";
        String password = "ciwkiciw";
        String sectionName = "kilat";
        String catatan = "catatan. test, 1-2-3";
        int qty = 4;
        String[] PIN = {"1","2","3","4","5","6"};
        bltc.beliLangsung1(email, password, sectionName, catatan, qty, PIN);
    }
}
