package tests.US_004;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.Driver;
import utilities.ExtentReport;
import utilities.ReusableMethods;
import utilities.TestMethods;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

public class TC_004 extends ExtentReport {


    @Test
    public void miktarArttirma() throws IOException {

        TestOtomasyonu to = new TestOtomasyonu();
        TestMethods test = new TestMethods();
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(Driver.getDriver());
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        /*REPORT*/
        extentTest = extent.createTest("Ürünün Miktarını Arttırarak Sepete Ekleme",
                "Kullanıcı ürün miktarını arttırarak ürün sepete eklemeli ve test etmeli.");

        //url` ye git
        //Sayfaya gittigini dogrula
        test.sayfaDogrula();

        //Arama kutusunun gorünür oldugunu test et
        Assert.assertTrue(to.searchBox.isDisplayed());

        //Arama kutusuna phone yaz
        //Enter'a bas
        actions.sendKeys(to.searchBox, "phone" + Keys.ENTER)
                .perform();

        //Sayfa url'sinin "https://testotomasyonu.com/search-product?search=phone&search_category=0" oldugunu test et
        test.URLTestEt("https://testotomasyonu.com/search-product?search=phone&search_category=0");

        //0'dan fazla urun bulundugunu dogrula
        test.kategorideUrunVarMi();

        //ilk urune tikla
        WebElement urun = Driver.getDriver().findElement(By.xpath("//div[2]/ul/li[1]/div/a"));
        softAssert.assertTrue(urun.isDisplayed());
        wait.until(ExpectedConditions.visibilityOf(urun)).click();

        //Sayfa url'sinin "https://testotomasyonu.com/product/" icerdigini test et
        test.URLTestEt("https://testotomasyonu.com/product/");

        //Miktar kutusunun gorunurlugunu dogrula
        softAssert.assertTrue(to.arttirmaButonu.isDisplayed());

        //Miktar kutusunda + butonuna 2 kere bas
        ReusableMethods.click(to.arttirmaButonu);
        ReusableMethods.click(to.arttirmaButonu);

        //Add to cart butonuna tikla
        ReusableMethods.click(to.addToCart);
        /*REPORT*/
        extentTest.info("Kullanıcı sepete ekle butonuna tıklar.");

        //Product Added To Cart! Yazisinin ciktigini dogrula
        softAssert.assertTrue(to.addedMessage.isDisplayed());
        /*REPORT*/
        extentTest.pass("Ürün sepete eklenmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String
                        (ReusableMethods.WEResmiBase64(to.addedMessage)).build());

        //Your Cart butonunun gorunurlugunu test et
        softAssert.assertTrue(to.cart.isDisplayed());

        //Your Cart butonunun sayisininin 3 oldugunu dogrula
        String sepetIkonundaGorunenUrunAdedi = to.cart.getText();
        softAssert.assertEquals(sepetIkonundaGorunenUrunAdedi, 3); //sepete 1 adet ürün eklenince sept ikonundaki sayının 1 olması test eediliyor.
        /*REPORT*/
        extentTest.pass("Sepete eklenen ürün sayısının, cart ikonunda da yazdığı doğrulanmıştır.");

        //Your cart butonuna tikla
        //Sayfa url'sinin "https://testotomasyonu.com/shoppingCart" oldugunu test et
        //Sepette urun bulundugunu test et
        //Urun miktarinin 3 oldugunu test et

        Map<String, Integer> sepettekiUrunler = test.sepetteBulunanUrunler();
        String isim = "APPLEL iPhone 13 (Starlight, 128 GB)";

        //map key değerleri ürün adı, value değerleri ürün miktarı olduğu için ürün miktarları görüntülenebilir.
        int urunMiktari = sepettekiUrunler.get(isim);
        softAssert.assertEquals(urunMiktari, 3);
        /*REPORT*/
        extentTest.pass("Sepete eklenen ürün ile sayfadaki ürün sayısının aynı olduğu test edilmiştir.");
    }
}
