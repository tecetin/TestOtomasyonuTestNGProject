package tests.US_001;


import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class TC_003 extends registrationBase {

    @Test
    public void negativeRegistrationBosBilgiler() throws InterruptedException, IOException {
        //url` ye git
        //Sayfaya gittigini dogrula
        //Gorunur durumdaysa Account' a tikla
        //Gorunur durumdaysa Sign Up' a tikla
        //Register Now sayfasina gelindigi dogrula

        /*REPORT*/
        extentTest = extent.createTest("Kullanıcı Negative Registration testi 1",
                "Kullanıcı Registration Now sayfasında bilgileri boş bırakarak girerek kayıt yapılamadığını doğrular");

        /*REPORT*/
        extentTest.info("Kullanıcı bilgileri boş bırakılarak Sign Up butonuna basar.");
        //Bilgileri bos birak
        wait.until(ExpectedConditions.elementToBeClickable(to.signupfirstName)).click();

        //Gorunur durumdaysa Sign Up' a tikla
        wait.until(ExpectedConditions.visibilityOf(to.signUpButton));
        actions.moveToElement(to.signUpButton)
                .click()
                .perform();

        /*REPORT*/
        extentTest.info("Kullanıcı bilgi alanlarında Required yazısı çıktığını görmelidir.");
        //Tum bilgiler icin required uyarisi verildigini dogrula
        List<String> requireds = ReusableMethods.toStringList(to.required);
        List<String> expReq = Arrays.asList(
                "First name",
                "Last name",
                "Email address",
                "Password",
                "Confirm password does not match"
        );

        //Her bir bosluk icin belirli kelimeleri tüm bosluklarin verdigi mesajlari iceren listede arar
        for (int i = 0; i < expReq.size(); i++) {
            String word = expReq.get(i);
            boolean var = false;

            for (String each : requireds) {

                if (each.contains(word)) {
                    var = true; // Kelime bulundu
                    /*REPORT*/
                    extentTest.pass(word + " için required uyarısı verildi.");
                    break;
                }
            }
            Assert.assertTrue(var, word + " icin gereklilik uyarısı verilmiyor.");
        }
        /*REPORT*/
        extentTest.addScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.loginForm), "Hata mesajları");

        //Kayit yapilamadigi ve Register Now sayfasinda kalindigini test et
        String expUrl = "https://testotomasyonu.com/customer-register";
        String actUrl = Driver.getDriver().getCurrentUrl();
        ReusableMethods.bekle(1); //ekran resmi çekemiyor
        try {
            Assert.assertEquals(actUrl, expUrl); // Doğrulama
            // Başarı raporu
            extentTest.pass("Kullanıcı kayit yapılamadığını ve Register Now sayfasında kalındığını görür.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()
                            + ReusableMethods.sayfaSSBase64()).build());
        } catch (AssertionError | IOException e) {
            // Başarısızlık raporu
            extentTest.fail("Kullanıcı bilgileri doldurmadığı olduğu halde kayıt yapabilmiştir.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());
        }
    }
}