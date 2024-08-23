package tests.US_001;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;

public class TC_004 extends registrationBase {

    @Test
    public void negativeRegistrationEmailHatali() throws InterruptedException, IOException {
        //url` ye git
        //Sayfaya gittigini dogrula
        //Gorunur durumdaysa Account' a tikla
        //Gorunur durumdaysa Sign Up' a tikla
        //Register Now sayfasina gelindigi dogrula

        /*REPORT*/
        extentTest = extent.createTest("Kullanıcı Negative Registration testi 2",
                "Kullanıcı Registration Now sayfasında emaili hatalı girerek kayıt yapılamadığını doğrular");

        /*REPORT*/
        extentTest.info("Kullanıcı tüm bilgileri doldurup, email adresini yanlış girerek Sign Up butonuna basar.");
        //Email adresi @ isareti kullanilmadan gir
        String yanlisEmail = ConfigReader.getProperty("email").replace("@", "");
        wait.until(ExpectedConditions.elementToBeClickable(to.signupfirstName)).click();
        actions.sendKeys(ConfigReader.getProperty("firstName") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("lastName") + Keys.TAB)
                .sendKeys(yanlisEmail + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("password") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("password"))
                .perform();

        //Gorunur durumdaysa Sign Up' a tikla
        wait.until(ExpectedConditions.visibilityOf(to.signUpButton));
        actions.moveToElement(to.signUpButton)
                .click()
                .perform();

        //Kayit yapilamadigi ve Register Now sayfasinda kalindigini test et
        String expUrl = "https://testotomasyonu.com/customer-register";
        String actUrl = Driver.getDriver().getCurrentUrl();
        ReusableMethods.bekle(1); //ekran resmi çekemiyor
        try {
            Assert.assertEquals(actUrl, expUrl); // Doğrulama
            //Başarı raporu
            extentTest.pass("Kullanıcı kayit yapılamadığını ve Register Now sayfasında kalındığını görür.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()
                            + ReusableMethods.WEResmiBase64(to.signupemail)).build());
        } catch (AssertionError | IOException e) {
            // Başarısızlık raporu
            extentTest.fail("Kullanıcı email hatalı olduğu halde kayıt yapabilmiştir.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());
        }
    }
}
