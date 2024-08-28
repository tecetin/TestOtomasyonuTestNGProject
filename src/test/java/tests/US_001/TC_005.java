package tests.US_001;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestOtomasyonu;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;

public class TC_005 extends registrationBase {

    @Test
    public void negativeRegistrationConfirmPasswordHatali() throws InterruptedException, IOException {
        //url` ye git
        //Sayfaya gittigini dogrula
        //Gorunur durumdaysa Account' a tikla
        //Gorunur durumdaysa Sign Up' a tikla
        //Register Now sayfasina gelindigi dogrula

        /*REPORT*/
        extentTest = extent.createTest("Kullanıcı Negative Registration testi 3",
                "Kullanıcı Registration Now sayfasında emaili hatalı girerek kayıt yapılamadığını doğrular");

        /*REPORT*/
        extentTest.info("Kullanıcı tüm bilgileri doldurup, Confirm Password bölümünü yanlış girerek Sign Up butonuna basar.");
        //Confirm password passworddan farkli gir
        String yanlisPassword = ConfigReader.getProperty("password").concat("abc");
        to = new TestOtomasyonu();
        actions = new Actions(Driver.getDriver());

        wait.until(ExpectedConditions.elementToBeClickable(to.signupfirstName)).click();
        actions.sendKeys(ConfigReader.getProperty("firstName") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("lastName") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("email") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("password") + Keys.TAB)
                .sendKeys(yanlisPassword)
                .perform();

        //Gorunur durumdaysa Sign Up' a tikla
        wait.until(ExpectedConditions.visibilityOf(to.signUpButton));
        actions.moveToElement(to.signUpButton)
                .click()
                .perform();

        ReusableMethods.bekle(1); //ekran resmi alamıyor
        /*REPORT*/
        extentTest.info("Confirm Password bölümü yanlış girilince hata mesajı vermelidir.");
        //Password hatasi verildigi dogrula
        Assert.assertTrue(to.confirmPasswordRequired.isDisplayed());
        /*REPORT*/
        extentTest.pass("Confirm Password için hata uyarısı verildi.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.confirmPasswordRequired)).build());

        //Kayit yapilamadigini test et
        String expUrl = "https://testotomasyonu.com/customer-register";
        String actUrl = Driver.getDriver().getCurrentUrl();
        try {
            Assert.assertEquals(actUrl, expUrl); // Doğrulama
            // Başarı raporu
            extentTest.pass("Kullanıcı kayit yapılamadığını ve Register Now sayfasında kalındığını görür.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());
        } catch (AssertionError | IOException e) {
            // Başarısızlık raporu
            extentTest.fail("Kullanıcı email hatalı olduğu halde kayıt yapabilmiştir.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());
        }
    }
}