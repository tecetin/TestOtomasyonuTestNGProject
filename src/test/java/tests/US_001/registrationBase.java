package tests.US_001;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.github.javafaker.Faker;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ExtentReport;
import utilities.ReusableMethods;

import java.io.IOException;
import java.time.Duration;


public class registrationBase extends ExtentReport {
    protected static SoftAssert softAssert = new SoftAssert();
    protected static WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    protected static Actions actions = new Actions(Driver.getDriver());
    protected static TestOtomasyonu to = new TestOtomasyonu();

    @BeforeMethod
    public void registerFirstSteps() throws IOException {
        // extentTest adımını başlat
        /*REPORT*/
        extentTest = extent.createTest("Registration Ortak Adımları", "Kullanıcı Registration Now sayfasında olduğunu doğrular");

        //url` ye git
        /*REPORT*/
        extentTest.info("Kullanıcı anasayfaya gider");
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //Sayfaya gittigini dogrula
        String title = "Test Otomasyonu";
        String actTitle = Driver.getDriver().getTitle();
        softAssert.assertTrue(actTitle.contains(title), "Anasayfa Test Otomasyonu başlığını içermiyor");

        /*REPORT*/
        extentTest.pass("Kullanıcı başarılı bir şekilde anasayfaya gittiğini doğrular",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        //Gorunur durumdaysa Account' a tikla
        /*REPORT*/
        extentTest.info("Kullanıcı Account'a tıklar");

        to = new TestOtomasyonu();
        wait.until(ExpectedConditions.elementToBeClickable(to.account)).click();

        //Gorunur durumdaysa Sign Up' a tikla
        /*REPORT*/
        extentTest.info("Kullanıcı Sign Up'a tıklar");
        wait.until(ExpectedConditions.elementToBeClickable(to.signUpLink)).click();

        //Register Now sayfasina gelindigi dogrula
        /*REPORT*/
        extentTest.info("Kullanıcı Register Now sayfasına gider");
        String formText = "Register Now";
        String actText = to.FormHead.getText();
        wait.until(ExpectedConditions.visibilityOf(to.FormHead));
        try {
            softAssert.assertEquals(actText, formText);
            /*REPORT*/
            extentTest.pass("Kullanıcı başarılı bir şekilde Register Now sayfasına gittiğini doğrular",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());
        } catch (Exception e) {
            /*REPORT*/
            extentTest.fail("Kullanıcı Register Now sayfasında değil");
        }
    }
}
