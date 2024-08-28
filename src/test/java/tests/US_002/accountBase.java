package tests.US_002;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ExtentReport;
import utilities.ReusableMethods;

import java.io.IOException;
import java.time.Duration;

public class accountBase extends ExtentReport {
    private static SoftAssert softAssert = new SoftAssert();
    private static WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    private static TestOtomasyonu to = new TestOtomasyonu();

    public static void logIn() throws IOException {

        //url` ye git
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        /*REPORT*/
        extentTest.info("Kullanıcı anasayfaya gider");

        //Sayfaya gittigini dogrula
        String title = "Test Otomasyonu";
        String actTitle = Driver.getDriver().getTitle();
        softAssert.assertTrue(actTitle.contains(title));
        /*REPORT*/
        extentTest.pass("Kullanıcı başarılı bir şekilde anasayfaya gittiğini doğrular",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        //Gorunur durumdaysa Account' a tikla
        /*REPORT*/
        extentTest.info("Kullanıcı Account'a tıklar");
        to = new TestOtomasyonu();
        wait.until(ExpectedConditions.elementToBeClickable(to.account)).click();

        //Login Now sayfasina gelindigini test et
        String formText = "Login Now";
        String actText = to.FormHead.getText();
        softAssert.assertEquals(actText, formText);
        extentTest.pass("Kullanıcı başarılı bir şekilde Log In sayfasına gittiğini doğrular",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        //Email ve password alanlarinin gorunur oldugunu dogrula
        softAssert.assertTrue(to.logInEmail.isDisplayed(), "Email alani görünür durumda değil.");
        /*REPORT*/
        extentTest.pass("Email elementi görünür durumdadır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.logInEmail)).build());

        softAssert.assertTrue(to.logInPassword.isDisplayed(), "Password alani görünür durumda değil.");
        /*REPORT*/
        extentTest.pass("Password elementi görünür durumdadır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.logInPassword)).build());

        //Gecerli email ve password gir
        /*REPORT*/
        extentTest.info("Kullanıcı geçerli bilgileri girer.");
        to.logInEmail.sendKeys(ConfigReader.getProperty("email"));
        to.logInPassword.sendKeys(ConfigReader.getProperty("password"));

        //Gorunur durumdaysa Sign in butonuna tikla
        /*REPORT*/
        extentTest.info("Kullanıcı Sign In butonuna tıklar.");
        wait.until(ExpectedConditions.elementToBeClickable(to.signIn)).click();

        //Login Success gorunurlugunu dogrula
        boolean success = false;
        try {
            wait.until(ExpectedConditions.visibilityOf(to.successBox));
            success = true;
            /*REPORT*/
            extentTest.pass("Kullanıcı başarılı bir şekilde giriş yapıldığını doğrular.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String
                            (ReusableMethods.WEResmiBase64(to.successBox)).build());
        } catch (Exception e) {
            /*REPORT*/
            extentTest.pass("Kullanıcı başarılı bir şekilde giriş yapılamadığını görür.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String
                            (ReusableMethods.sayfaSSBase64()).build());
        }
        Assert.assertTrue(success, "Giriş yapilamadi.");

        //Kullanici profiline yonlendirildigini dogrula
        String expUrl = "https://testotomasyonu.com/user-dashboard";
        String actUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(actUrl, expUrl);
    }
}
