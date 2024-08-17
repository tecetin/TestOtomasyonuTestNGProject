package tests.US_001;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_005 extends registrationBase {

    @Test
    public void negativeRegistrationConfirmPasswordHatali() throws InterruptedException {
        //url` ye git
        //Sayfaya gittigini dogrula
        //Gorunur durumdaysa Account' a tikla
        //Gorunur durumdaysa Sign Up' a tikla
        //Register Now sayfasina gelindigi dogrula

        //Confirm password passworddan farkli gir
        String yanlisPassword = ConfigReader.getProperty("password").concat("abc");
        wait.until(ExpectedConditions.elementToBeClickable(testOtomasyonuPage.signupfirstName)).click();
        actions.sendKeys(ConfigReader.getProperty("firstName") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("lastName") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("email") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("password") + Keys.TAB)
                .sendKeys(yanlisPassword)
                .perform();

        //Gorunur durumdaysa Sign Up' a tikla
        wait.until(ExpectedConditions.visibilityOf(testOtomasyonuPage.signUpButton));
        actions.moveToElement(testOtomasyonuPage.signUpButton)
                .click()
                .perform();

        //Password hatasi verildigi dogrula
        Assert.assertTrue(testOtomasyonuPage.confirmPasswordRequired.isDisplayed());

        //Kayit yapilamadigini test et
        String expUrl = "https://testotomasyonu.com/customer-register";
        String actUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actUrl, expUrl);

    }
}