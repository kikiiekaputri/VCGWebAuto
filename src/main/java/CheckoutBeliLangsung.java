import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutBeliLangsung {

    WebDriver driver;
    By pageTitle = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/p");
    By linkInputCatatan = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[1]/div/div/div[2]/div[2]");
    By tambahQtyBtn = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[1]/div/div/div[2]/div[3]/div/button[2]");
    By kurangQtyBtn = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[1]/div/div/div[2]/div[3]/div/button[1]");
    By choosePayMethod = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[2]/div/div[3]");

    public CheckoutBeliLangsung(WebDriver driver) {
        this.driver = driver;
    }

    public void waitPageLoaded() throws NoSuchElementException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(pageTitle));
    }

    public void increaseQuantity(int counter) throws InterruptedException {
        WebElement plusBtn = driver.findElement(tambahQtyBtn);
        int i = 0;

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

        while (minBtn.isEnabled() && i<counter) {
            // Tombol A diaktifkan, lakukan tindakan yang diinginkan (misalnya, klik tombol A)
            minBtn.click();

            // Tunggu beberapa saat untuk memungkinkan perubahan halaman atau aksi setelah mengklik tombol A
            Thread.sleep(1000); // Sesuaikan dengan kebutuhan

            // Perbarui status tombol A setelah melakukan tindakan
            i++;
            minBtn = driver.findElement(kurangQtyBtn);
        }
    }

    public void openPaymentMethodBox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement boxPayMethod = wait.until(ExpectedConditions.presenceOfElementLocated(choosePayMethod));
        //klik modal Pilih Metode Pembayaran
        boxPayMethod.click();
    }

    public void choosePaymentMethod(String method) {
        By chosenMethod = null;
        if(method.equalsIgnoreCase("vc coin")) {
            chosenMethod = By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[15]/div/div[2]/div[1]/div");
        } else if(method.equalsIgnoreCase("VA")) {
            chosenMethod = By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[15]/div/div[2]/div[2]/div[1]/div[9]");
        } else if(method.equalsIgnoreCase("ewallet")||method.equalsIgnoreCase("e-wallet")) {
            chosenMethod = By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[15]/div/div[2]/div[2]/div[2]/div[8]");
        } else {
            chosenMethod = By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[15]/div/div[2]/div[2]/div[3]/div[2]");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(chosenMethod)).click();
    }

    public void clickBayarButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement bayarButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[2]/div/button")));
        bayarButton.click();
    }

    public void clickLanjutPembayaranButton() {
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement lanjut = wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[19]/div/div[2]/div[2]/div[2]/button")));
        lanjut.click();
    }

    public void clickKembaliButton() {
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement kembali = wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[19]/div/div[2]/div[2]/div[1]/button")));
        kembali.click();
    }

    public void inputPIN(String[] pinDigits) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[17]/div/div/div[2]/div/div[1]/input")));

        for (int i = 0; i < pinDigits.length; i++) {
            String digit = pinDigits[i];
            WebElement pinFields = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[17]/div/div/div[2]/div/div["+(i+1)+"]/input"));
            pinFields.sendKeys(pinDigits[i]);
        }

        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div/div[1]/div[1]/img")));
    }

    public boolean validatePIN(String[] pinDigits) throws InterruptedException {
        Thread.sleep(3000);

        By alert = By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[17]/div/div[1]/p");
        List<WebElement> errorMessage = driver.findElements(alert);
        if(errorMessage.size() > 0) {
            System.out.println(errorMessage.get(0).getText());
            return false;
        } else {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div/div[1]/div[1]/img")));
            System.out.println("PIN benar");
            return true;
        }
    }

    public boolean isVCCoinEnough() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            WebElement errMsg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[15]/div/div[2]/div[1]/div/div[1]/div[2]/div/p")));
            System.out.println("VC Coin tidak cukup");
            return false;
        } catch (NoSuchElementException ne) {
            System.out.println("VC Coin cukup");
            return true;
        }
    }

}
