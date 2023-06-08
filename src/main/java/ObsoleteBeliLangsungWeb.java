import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ObsoleteBeliLangsungWeb extends ObsoleteLoginWeb {

    String messages;

    public ObsoleteBeliLangsungWeb() {
        messages = "";
    }

    //beli produk dari homepage (kilat)
    private void chooseProduct() {
        //tunggu sampe homepage loaded completely
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Jika prompt muncul, maka klik background
            //WebElement prompt = wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"label1\"]")));
            WebElement promptBG = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"hsl-00B__031-backdrop\"]")));
            promptBG.click();
        } catch (NoSuchElementException e) {

        }
        //WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div/div/div[1]/div[1]/p")));

        //scroll page sampe section ditemukan
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sectionTarget = driver.findElement(By.id("home-kilat-slider-4"));
        js.executeScript("arguments[0].scrollIntoView(true);",sectionTarget);

        //tunggu sampe ketemu
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //cari produk kilat di section tsb
        WebElement product = driver.findElement(By.xpath("//*[@id=\"home-kilat-slider-4\"]/div[2]/a"));
        product.click();
    }

    private void checkOut(String catatan, int qty) {
        //tunggu sampai page detail product loaded completely
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[2]/h1")));

        //isi catatan
        WebElement fieldCatatan = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[2]/div[1]/div/textarea"));
        fieldCatatan.sendKeys(catatan);

        //tambah qty
        WebElement quantity = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[2]/div[1]/div/div[1]/div/button[2]"));
        for(int i=1; i<=qty; i++) {
            quantity.click();
        }

        //click button Beli Langsung
        WebElement buttonBL = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[2]/div[1]/div/div[3]/div[2]/button"));
        buttonBL.click();
    }

    private void choosePaymentMethod() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement listPayMethod = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[2]/div/div[3]")));

        //klik modal Pilih Metode Pembayaran
        listPayMethod.click();

        //pilih VC Coin
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement chosenMethod = wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[15]/div/div[2]/div[1]/div")));
        chosenMethod.click();
    }

    private void payProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement bayarButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[2]/div[2]/div/button")));
        bayarButton.click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement lanjut = wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[19]/div/div[2]/div[2]/div[2]/button")));
        lanjut.click();
    }

    private void inputPIN(String[] pinDigits) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[17]/div/div/div[2]/div/div[1]/input")));

        for (int i = 0; i < pinDigits.length; i++) {
            String digit = pinDigits[i];
            WebElement pinFields = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[3]/div[17]/div/div/div[2]/div/div["+(i+1)+"]/input"));
            pinFields.sendKeys(pinDigits[i]);
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div/div[1]/div[1]/img")));
    }

    public void doBeliLangsung(String catatan, int qty, String[] pinDigits) {
        chooseProduct();
        checkOut(catatan, qty);
        choosePaymentMethod();
        payProduct();
        inputPIN(pinDigits);
    }
}
