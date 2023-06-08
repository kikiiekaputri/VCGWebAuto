import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DetailProductPage {

    WebDriver driver;
    By catatanField = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[2]/div[1]/div/textarea");
    By tambahQtyBtn = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[2]/div[1]/div/div[1]/div/button[2]");
    By kurangQtyBtn = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[2]/div[1]/div/div[1]/div/button[1]");
    By addCartBtn = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[2]/div[1]/div/div[3]/div[1]/button");
    By beliLangsungBtn = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[2]/div[1]/div/div[3]/div[2]/button");
    By promptBtn = By.id("btn1");
    By promptBG = By.id("hsl-00E__031-backdrop");

    public DetailProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void closePrompt() throws NoSuchElementException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // Jika prompt muncul, maka klik button Oke
        wait.until(ExpectedConditions.presenceOfElementLocated(promptBtn));
        driver.findElement(promptBtn).click();
    }

    public void fillCatatanField(String textCatatan) {
        driver.findElement(catatanField).sendKeys(textCatatan);
    }

    public void increaseQuantity(int counter) throws InterruptedException {
        WebElement plusBtn = driver.findElement(tambahQtyBtn);
        int i = 1;

        while (plusBtn.isEnabled() && i<counter) {
            // Tombol A diaktifkan, lakukan tindakan yang diinginkan (misalnya, klik tombol A)
            plusBtn.click();

            // Tunggu beberapa saat untuk memungkinkan perubahan halaman atau aksi setelah mengklik tombol A
            Thread.sleep(1000); // Sesuaikan dengan kebutuhan

            // Perbarui status tombol A setelah melakukan tindakan
            i++;
            plusBtn = driver.findElement(tambahQtyBtn);
        }
    }

    public void decreaseQuantity(int counter) throws InterruptedException {
        WebElement minBtn = driver.findElement(kurangQtyBtn);
        int i = 0;

        while (minBtn.isEnabled() || i<counter) {
            // Tombol A diaktifkan, lakukan tindakan yang diinginkan (misalnya, klik tombol A)
            minBtn.click();

            // Tunggu beberapa saat untuk memungkinkan perubahan halaman atau aksi setelah mengklik tombol A
            Thread.sleep(1000); // Sesuaikan dengan kebutuhan

            // Perbarui status tombol A setelah melakukan tindakan
            i++;
            minBtn = driver.findElement(kurangQtyBtn);
        }
    }

    public void clickBeliLangsung() {
        driver.findElement(beliLangsungBtn).click();
    }

    public void clickAddToCart() {
        driver.findElement(addCartBtn).click();
    }

    public boolean isUserLoggedIn() throws InterruptedException {
        Thread.sleep(5000);

        By checkoutpage = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/p");
        By loginpage = By.xpath("//*[@id=\"login-page\"]/div[1]/div[2]/div/div[1]/form/input");
        WebElement target = null;
        try {
            target = driver.findElement(checkoutpage);
            System.out.println("logged in");
            return true;
        } catch (NoSuchElementException ne) {
            target = driver.findElement(loginpage);
            System.out.println("not logged in");
            return false;
        }
    }


}
