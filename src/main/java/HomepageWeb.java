import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class HomepageWeb {

    WebDriver driver;
    By prompt = By.xpath("//*[@id=\"label1\"]");
    By promptBG = By.xpath("//*[@id=\"hsl-00B__031-backdrop\"]");
    By masukDaftarBtn = By.xpath("//*[@id=\"__layout\"]/div/div[1]/header/div[3]/div/div/a");
    By sectionKilat = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[6]/div[1]/div[1]/p[2]");
    By sectionInstant = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[5]/div[1]/div[1]/p[2]");

    public HomepageWeb(WebDriver driver) {
        this.driver = driver;
    }

    public void closePrompt() throws NoSuchElementException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Jika prompt muncul, maka klik background
        wait.until(ExpectedConditions.presenceOfElementLocated(promptBG));
        driver.findElement(promptBG).click();
    }

    public void clickMasukDaftarBtn() {
        driver.findElement(masukDaftarBtn).click();
    }

    public void findSection(String sectionName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sectionTarget = null;
        if (sectionName.equalsIgnoreCase("kilat")) {
            sectionTarget = driver.findElement(sectionKilat);
        } else if (sectionName.equalsIgnoreCase("instant")) {
            sectionTarget = driver.findElement(sectionInstant);
        } else if (sectionName.equalsIgnoreCase("gercep")) {
            //target section Gercep
        }
        js.executeScript("arguments[0].scrollIntoView(true);",sectionTarget);

        //tunggu sampe ketemu
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickItem(String sectionName) {
        int div = 0;
        if (sectionName.equalsIgnoreCase("kilat")) {
            div = 4;
        } else if (sectionName.equalsIgnoreCase("instant")) {
            div = 3;
        }
        String sn = sectionName.toLowerCase();
        WebElement item = driver.findElement(By.xpath("//*[@id=\"home-"+sn+"-slider-"+div+"\"]/div[2]/a"));
        item.click();
    }


}
