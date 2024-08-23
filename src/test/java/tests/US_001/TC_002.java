package tests.US_001;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReusableMethods;

import java.io.IOException;

public class TC_002 extends registrationBase {

    @Test
    public void positiveRegistration() throws IOException {
        //url` ye git
        //Sayfaya gittigini dogrula
        //Gorunur durumdaysa Account' a tikla
        //Gorunur durumdaysa Sign Up' a tikla
        //Register Now sayfasina gelindigi dogrula

        /*REPORT*/
        extentTest = extent.createTest("Kullanıcı kaydı testi",
                "Kullanıcı Registration Now sayfasında geçerli bilgileri girerek kayıt yapılabildiğini doğrular");

        /*REPORT*/
        extentTest.info("Kullanıcı bilgileri oluşturulur");
        //fake kullanici bilgileri olustur ve propertiese kaydet
        String[] bilgiler = faker();
        String firstname = bilgiler[0];
        String lastname = bilgiler[1];
        String email = bilgiler[2];
        String password = bilgiler[3];

        /*REPORT*/
        extentTest.info("Kullanıcı bilgi girilmesi gereken alanları eksiksiz ve geçerli bir şekilde doldurur.");
        //Tum bilgileri gecerli olarak gir
        wait.until(ExpectedConditions.elementToBeClickable(to.signupfirstName)).click();
        actions.sendKeys(firstname + Keys.TAB)
                .sendKeys(lastname + Keys.TAB)
                .sendKeys(email + Keys.TAB)
                .sendKeys(password + Keys.TAB)
                .sendKeys(password)
                .perform();

        /*REPORT*/
        extentTest.info("Kullanıcı sign up butonuna tıklar.");
        //Gorunur durumdaysa Sign Up' a tikla
        wait.until(ExpectedConditions.visibilityOf(to.signUpButton));
        actions.moveToElement(to.signUpButton)
                .click()
                .perform();

        //Basarili kayit yapildigini test et
        boolean success = false;
        try {
            wait.until(ExpectedConditions.visibilityOf(to.successBox));
            success = true;
            /*REPORT*/
            extentTest.pass("Kullanıcı başarılı bir şekilde kayıt yapıldığını doğrular.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.successBox)).build());
        } catch (Exception e) {
            /*REPORT*/
            extentTest.pass("Kullanıcı başarılı bir şekilde kayıt yapılamadığını görür.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());
        }
        Assert.assertTrue(success, "Kayit yapilamadi.");
    }
}
