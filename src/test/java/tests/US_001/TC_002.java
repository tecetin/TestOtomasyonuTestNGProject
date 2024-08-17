package tests.US_001;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_002 extends registrationBase {

    @Test
    public void positiveRegistration(){
        //url` ye git
        //Sayfaya gittigini dogrula
        //Gorunur durumdaysa Account' a tikla
        //Gorunur durumdaysa Sign Up' a tikla
        //Register Now sayfasina gelindigi dogrula

        //fake kullanici bilgileri olustur ve propertiese kaydet
        String[] bilgiler = faker();
        String firstname = bilgiler[0];
        String lastname = bilgiler[1];
        String email = bilgiler[2];
        String password = bilgiler[3];

        //Tum bilgileri gecerli olarak gir
        wait.until(ExpectedConditions.elementToBeClickable(testOtomasyonuPage.signupfirstName)).click();
        actions.sendKeys(firstname + Keys.TAB)
                .sendKeys(lastname + Keys.TAB)
                .sendKeys(email + Keys.TAB)
                .sendKeys(password + Keys.TAB)
                .sendKeys(password)
                .perform();

        //Gorunur durumdaysa Sign Up' a tikla
        wait.until(ExpectedConditions.visibilityOf(testOtomasyonuPage.signUpButton));
        actions.moveToElement(testOtomasyonuPage.signUpButton)
                .click()
                .perform();

        //Basarili kayit yapildigini test et
        boolean success = false;
        try {
            wait.until(ExpectedConditions.visibilityOf(testOtomasyonuPage.successBox));
            success = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(success, "Kayit yapilamadi.");
    }
}
