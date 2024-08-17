package tests.US_002;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;

public class accountBase {

    static SoftAssert softAssert = new SoftAssert();
    static WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    static TestOtomasyonu testOtomasyonuPage = new TestOtomasyonu();
    static Actions actions = new Actions(Driver.getDriver());

    @BeforeTest
    public void logIn(){

        //url` ye git
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //Sayfaya gittigini dogrula
        String title = "Test Otomasyonu";
        String actTitle = Driver.getDriver().getTitle();
        softAssert.assertTrue(actTitle.contains(title));

        //Gorunur durumdaysa Account' a tikla
        wait.until(ExpectedConditions.elementToBeClickable(testOtomasyonuPage.account)).click();

        //Login Now sayfasina gelindigini test et
        String formText = "Login Now";
        String actText = testOtomasyonuPage.logInFormHead.getText();
        softAssert.assertEquals(actText, formText);

        //Email ve password alanlarinin gorunur oldugunu dogrula
        softAssert.assertTrue(testOtomasyonuPage.logInEmail.isDisplayed(), "Email alani görünür durumda değil.");
        softAssert.assertTrue(testOtomasyonuPage.logInPassword.isDisplayed(), "Password alani görünür durumda değil.");

        //Gecerli email ve password gir
        testOtomasyonuPage.logInEmail.sendKeys(ConfigReader.getProperty("email"));
        testOtomasyonuPage.logInPassword.sendKeys(ConfigReader.getProperty("password"));

        //Gorunur durumdaysa Sign in butonuna tikla
        wait.until(ExpectedConditions.elementToBeClickable(testOtomasyonuPage.signIn)).click();

        //Login Success gorunurlugunu dogrula
        boolean success = false;
        try {
            wait.until(ExpectedConditions.visibilityOf(testOtomasyonuPage.successBox));
            success = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(success, "Kayit yapilamadi.");

        //Kullanici profiline yonlendirildigini dogrula
        String expUrl = "https://testotomasyonu.com/user-dashboard";
        String actUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(actUrl, expUrl);
    }


    @AfterTest
    public void quitDriver(){
        ReusableMethods.bekle(1);
        Driver.quitDriver();
    }
}
