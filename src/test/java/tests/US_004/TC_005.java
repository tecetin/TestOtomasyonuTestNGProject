package tests.US_004;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.*;

import java.io.IOException;
import java.time.Duration;

public class TC_005 extends ExtentReport {

    TestOtomasyonu to = new TestOtomasyonu();
    SoftAssert softAssert = new SoftAssert();
    Actions actions = new Actions(Driver.getDriver());
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    @Test
    public void sepettenSilGirisYapmadanAlisveris() throws IOException {
        /*REPORT*/
        extentTest = extent.createTest("Sipariş Oluşturma",
                "Kullanıcı giriş yapmadan sepete eklediği ürünün siparişini oluşturduğunu doğrular.");

        //url` ye git
        //Sayfaya gittigini dogrula
        //Top Selling Products bolumune git
        //View All Products butonunun tiklanabilirligini test et
        //View All Products butonuna tikla
        //Sayfa url'sinin "https://testotomasyonu.com/trending/all-products" oldugunu test et
        //Kategoride 0'dan fazla urun bulundugunu dogrula
        //Listedeki ilk urunun uzerine git
        //Urun kutusunun uzerinde dururken gorunur olan Add to Cart butonunun tiklanabilirligini test et
        //Add to cart butonuna tikla
        //Product Added To Cart! Yazisinin ciktigini dogrula
        //Your Cart butonunun gorunurlugunu test et
        //Your Cart butonunun sayisininin 1 oldugunu dogrula
        //Your cart butonuna tikla
        //Sayfa url'sinin "https://testotomasyonu.com/shoppingCart" oldugunu test et
        //Sepette urun bulundugunu test et
        TC_002 test2 = new TC_002();
        test2.runValidation = false;
        test2.urunKontrolu();
        extentTest.info("Sepete ürün eklendi ve sepette ürün olduğu kontrol edilir.");

        //Checkout butonuna tikla
        extentTest.info("Ödeme adımına geçmek için checkout butonuna tıklanır.");
        ReusableMethods.click(to.cart);
        ReusableMethods.click(to.checkout);

        //Billing Address gorunurlugunu test et
        softAssert.assertTrue(to.billingAddressEkle.isDisplayed());
        extentTest.pass("Billing Address bölümünün görünürlüğü test edilmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.billingAddressEkle)).build());


        //Add Address butonuna tikla
        extentTest.info("Add Address butonuna tıklanır, tüm bilgiler bilgiler girilir.");
        ReusableMethods.click(to.billingAddressEkle);

        //Name, address, address 2, city, postcode, ülke ve sehir bilgilerini doldur
        //Add Address butonuna tikla
        wait.until(ExpectedConditions.visibilityOf(to.isimKutusu));
        actions.sendKeys(to.isimKutusu, ConfigReader.getProperty("firstName") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("email") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("phone") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("address") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("address") + Keys.TAB)
                .pause(Duration.ofSeconds(1))
                .sendKeys(ConfigReader.getProperty("country") + Keys.TAB)
                .pause(Duration.ofSeconds(1))
                .sendKeys(ConfigReader.getProperty("state") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("city") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("postcode"))
                .perform();
        ReusableMethods.click(to.addAddressButtonForm);

        //Billing Address listesinde adres bulundugunu test et
        wait.until(ExpectedConditions.visibilityOf(to.billingAddSec));
        softAssert.assertTrue(to.billingAddSec.isDisplayed());
        extentTest.pass("Billing Address bölümüne adres eklendiği test edilmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        //Billing Address listesinde adres sec
        extentTest.info("Billing Address seçilir.");
        ReusableMethods.click(to.billingAddSec);

        //Delivery address same as billing address kutucuguna tikla
        extentTest.info("Delivery address same as billing address kutucuğu seçilir.");
        ReusableMethods.click(to.ayniAdresSecButonu);

        //Delivery Address bolumunun gorunur olmadigini test et
        Assert.assertFalse(to.deliveryAddressEkle.isDisplayed());
        extentTest.pass("Delivery Address bölümünün görünür olmadığı test edilmiştir.");

        //Delivery address same as billing address kutucugunu unchecked yap
        extentTest.info("Delivery address same as billing address kutucuğu işareti kaldırılır.");
        ReusableMethods.click(to.ayniAdresSecButonu);

        //Delivery Address bolumunun gorunur oldugunu test et
        wait.until(ExpectedConditions.visibilityOf(to.deliveryAddressEkle));
        softAssert.assertTrue(to.deliveryAddressEkle.isDisplayed());
        extentTest.pass("Delivery Address bölümünün görünür olduğu test edilmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.deliveryAddressEkle)).build());

        //Add Address butonuna tikla
        extentTest.info("Add Address butonuna tıklanır, tüm bilgiler bilgiler girilir.");
        ReusableMethods.click(to.deliveryAddressEkle);

        //Name, address, address 2, city, postcode, ülke ve sehir bilgilerini doldur
        //Add Address butonuna tikla
        wait.until(ExpectedConditions.visibilityOf(to.isimKutusu));
        actions.sendKeys(to.isimKutusu, ConfigReader.getProperty("firstName") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("email") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("phone") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("address") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("address") + Keys.TAB)
                .pause(Duration.ofSeconds(1))
                .sendKeys(ConfigReader.getProperty("country") + Keys.TAB)
                .pause(Duration.ofSeconds(1))
                .sendKeys(ConfigReader.getProperty("state") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("city") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("postcode"))
                .perform();

        ReusableMethods.click(to.addAddressButtonForm);

        //Delivery Address listesinde adres bulundugunu test et
        wait.until(ExpectedConditions.visibilityOf(to.deliveryAddSec));
        softAssert.assertTrue(to.deliveryAddSec.isEnabled());
        extentTest.pass("Delivery Address bölümüne adres eklendiği test edilmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        //Billing Address listesinde adres sec
        extentTest.info("Delivery Address seçilir.");
        ReusableMethods.click(to.billingAddSec);

        //Delivery Address listesinde adres sec
        ReusableMethods.click(to.deliveryAddSec);

        //Shipping Methods listesinden kargo sec
        extentTest.info("Shipping Methods listesinden kargo seçilir.");
        ReusableMethods.click(to.freeShippingSec);

        //Terms and Conditions boxini checkle
        extentTest.info("Terms and Conditions kutucuğu seçilir.");
        ReusableMethods.click(to.policySec);

        //Place Order Now butonuna tikla
        extentTest.info("Place Order Now butonuna tıklanır.");
        ReusableMethods.click(to.placeOrderNow);

        //Your order is successfully done! Yazisinin gorunur oldugunu test et
        softAssert.assertTrue(to.orderSuccess.isDisplayed());
        extentTest.pass("Your order is successfully done! yazısının göründüğü test edilmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());
    }

    @Test
    public void sepettenSilGirisYaparakAlisveris() throws IOException {
        /*REPORT*/
        extentTest = extent.createTest("Sipariş Oluşturma",
                "Kullanıcı giriş yaparak sepete eklediği ürünün siparişini oluşturduğunu doğrular.");

        //url` ye git
        //Sayfaya gittigini dogrula
        //Kullanici girisi yap
        tests.US_002.accountBase.logIn();
        extentTest.info("Geçerli kullanıcı adı ve şifreyle giriş yapılır.");


        //Top Selling Products bolumune git
        //View All Products butonunun tiklanabilirligini test et
        //View All Products butonuna tikla
        //Sayfa url'sinin "https://testotomasyonu.com/trending/all-products" oldugunu test et
        //Kategoride 0'dan fazla urun bulundugunu dogrula
        //Listedeki ilk urunun uzerine git
        //Urun kutusunun uzerinde dururken gorunur olan Add to Cart butonunun tiklanabilirligini test et
        //Add to cart butonuna tikla
        //Product Added To Cart! Yazisinin ciktigini dogrula
        //Your Cart butonunun gorunurlugunu test et
        //Your Cart butonunun sayisininin 1 oldugunu dogrula
        //Your cart butonuna tikla
        //Sayfa url'sinin "https://testotomasyonu.com/shoppingCart" oldugunu test et
        //Sepette urun bulundugunu test et
        TC_002 test2 = new TC_002();
        test2.runValidation = false;
        test2.urunKontrolu();
        extentTest.info("Sepete ürün eklendi ve sepette ürün olduğu kontrol edilir.");

        //Checkout butonuna tikla
        extentTest.info("Ödeme adımına geçmek için checkout butonuna tıklanır.");
        ReusableMethods.click(to.cart);
        ReusableMethods.click(to.checkout);

        //Billing Address gorunurlugunu test et
        softAssert.assertTrue(to.billingAddressEkle.isDisplayed());
        extentTest.pass("Billing Address bölümünün görünürlüğü test edilmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.billingAddressEkle)).build());

        //Add Address butonuna tikla
        extentTest.info("Add Address butonuna tıklanır, tüm bilgiler bilgiler girilir.");
        ReusableMethods.click(to.billingAddressEkle);

        //Name, address, address 2, city, postcode, ülke ve sehir bilgilerini doldur
        //Add Address butonuna tikla
        wait.until(ExpectedConditions.visibilityOf(to.isimKutusu));
        actions.sendKeys(to.isimKutusu, ConfigReader.getProperty("firstName") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("address") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("address") + Keys.TAB)
                .pause(Duration.ofSeconds(1))
                .sendKeys(ConfigReader.getProperty("country") + Keys.TAB)
                .pause(Duration.ofSeconds(1))
                .sendKeys(ConfigReader.getProperty("state") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("city") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("postcode"))
                .perform();
        ReusableMethods.click(to.addAddressButtonForm);

        //Billing Address listesinde adres bulundugunu test et
        wait.until(ExpectedConditions.visibilityOf(to.billingAddSec));
        softAssert.assertTrue(to.billingAddSec.isDisplayed());
        extentTest.pass("Billing Address bölümüne adres eklendiği test edilmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        //Billing Address listesinde adres sec
        extentTest.info("Billing Address seçilir.");
        ReusableMethods.click(to.billingAddSec);

        //Delivery address same as billing address kutucuguna tikla
        extentTest.info("Delivery address same as billing address kutucuğu seçilir.");
        ReusableMethods.click(to.ayniAdresSecButonu);

        //Delivery Address bolumunun gorunur olmadigini test et
        Assert.assertFalse(to.deliveryAddressEkle.isDisplayed());
        extentTest.pass("Delivery Address bölümünün görünür olmadığı test edilmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.billingAddressEkle)).build());

        //Delivery address same as billing address kutucugunu unchecked yap
        extentTest.info("Delivery address same as billing address kutucuğu işareti kaldırılır.");
        ReusableMethods.click(to.ayniAdresSecButonu);

        //Delivery Address bolumunun gorunur oldugunu test et
        wait.until(ExpectedConditions.visibilityOf(to.deliveryAddressEkle));
        softAssert.assertTrue(to.deliveryAddressEkle.isDisplayed());
        extentTest.pass("Delivery Address bölümünün görünür olduğu test edilmiştir.");

        //Add Address butonuna tikla
        extentTest.info("Add Address butonuna tıklanır, tüm bilgiler bilgiler girilir.");
        ReusableMethods.click(to.deliveryAddressEkle);

        //Name, address, address 2, city, postcode, ülke ve sehir bilgilerini doldur
        //Add Address butonuna tikla
        wait.until(ExpectedConditions.visibilityOf(to.isimKutusu));
        actions.sendKeys(to.isimKutusu, ConfigReader.getProperty("firstName") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("address") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("address") + Keys.TAB)
                .pause(Duration.ofSeconds(1))
                .sendKeys(ConfigReader.getProperty("country") + Keys.TAB)
                .pause(Duration.ofSeconds(1))
                .sendKeys(ConfigReader.getProperty("state") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("city") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("postcode"))
                .perform();

        ReusableMethods.click(to.addAddressButtonForm);

        //Delivery Address listesinde adres bulundugunu test et
        wait.until(ExpectedConditions.visibilityOf(to.deliveryAddSec));
        softAssert.assertTrue(to.deliveryAddSec.isEnabled());
        extentTest.pass("Delivery Address bölümüne adres eklendiği test edilmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        //Billing Address listesinde adres sec
        extentTest.info("Delivery Address seçilir.");
        ReusableMethods.click(to.billingAddSec);

        //Delivery Address listesinde adres sec
        ReusableMethods.click(to.deliveryAddSec);

        //Shipping Methods listesinden kargo sec
        extentTest.info("Shipping Methods listesinden kargo seçilir.");
        ReusableMethods.click(to.freeShippingSec);

        //Terms and Conditions boxini checkle
        extentTest.info("Terms and Conditions kutucuğu seçilir.");
        ReusableMethods.click(to.policySec);

        //Place Order Now butonuna tikla
        extentTest.info("Place Order Now butonuna tıklanır.");
        ReusableMethods.click(to.placeOrderNow);

        //Your order is successfully done! Yazisinin gorunur oldugunu test et
        softAssert.assertTrue(to.orderSuccess.isDisplayed());
        extentTest.pass("Your order is successfully done! yazısının göründüğü test edilmiştir.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());
    }
}
