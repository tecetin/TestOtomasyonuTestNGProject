package tests.US_001;


import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.Arrays;
import java.util.List;


public class TC_003 extends registrationBase{

    @Test
    public void negativeRegistrationBosBilgiler() throws InterruptedException {
        //url` ye git
        //Sayfaya gittigini dogrula
        //Gorunur durumdaysa Account' a tikla
        //Gorunur durumdaysa Sign Up' a tikla
        //Register Now sayfasina gelindigi dogrula

        //Bilgileri bos birak
        wait.until(ExpectedConditions.elementToBeClickable(testOtomasyonuPage.signupfirstName)).click();

        //Gorunur durumdaysa Sign Up' a tikla
        wait.until(ExpectedConditions.visibilityOf(testOtomasyonuPage.signUpButton));
        actions.moveToElement(testOtomasyonuPage.signUpButton)
                .click()
                .perform();

        //Tum bilgiler icin required uyarisi verildigini dogrula
        List<String> requireds = ReusableMethods.toStringList(testOtomasyonuPage.required);
        List<String> expReq = Arrays.asList(
                "First name",
                "Last name",
                "Email address",
                "Password",
                "Confirm password does not match"
        );

        //Her bir bosluk icin belirli kelimeleri tüm bosluklarin verdigi mesajlari iceren şistede arar
        for (int i = 0; i < expReq.size(); i++) {
            String word = expReq.get(i);
            boolean var = false;

            for (String each : requireds) {

                if (each.contains(word)) {
                    var = true; // Kelime bulundu
                    break;
                }
            }
            Assert.assertTrue(var, word + " icin gereklilik uyarısı verilmiyor.");
        }

        //Kayit yapilamadigi ve Register Now sayfasinda kalindigini test et
        String expUrl = "https://testotomasyonu.com/customer-register";
        String actUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actUrl, expUrl);

    }

}