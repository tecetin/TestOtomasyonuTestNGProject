package tests.US_001;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_004 extends registrationBase {

    @Test
    public void negativeRegistrationEmailHatali() throws InterruptedException {
        //url` ye git
        //Sayfaya gittigini dogrula
        //Gorunur durumdaysa Account' a tikla
        //Gorunur durumdaysa Sign Up' a tikla
        //Register Now sayfasina gelindigi dogrula

        //Email adresi @ isareti kullanilmadan gir
        String yanlisEmail = ConfigReader.getProperty("email").replace("@", "");
        wait.until(ExpectedConditions.elementToBeClickable(testOtomasyonuPage.signupfirstName)).click();
        actions.sendKeys(ConfigReader.getProperty("firstName") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("lastName") + Keys.TAB)
                .sendKeys(yanlisEmail + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("password") + Keys.TAB)
                .sendKeys(ConfigReader.getProperty("password"))
                .perform();

        //Gorunur durumdaysa Sign Up' a tikla
        wait.until(ExpectedConditions.visibilityOf(testOtomasyonuPage.signUpButton));
        actions.moveToElement(testOtomasyonuPage.signUpButton)
                .click()
                .perform();

        //Kayit yapilamadigi ve Register Now sayfasinda kalindigini test et
        String expUrl = "https://testotomasyonu.com/customer-register";
        String actUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actUrl, expUrl);

    }
}
