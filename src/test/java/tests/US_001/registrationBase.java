package tests.US_001;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.ConfigReader;

import utilities.Driver;
import utilities.ExtentReport;
import utilities.ReusableMethods;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class registrationBase extends ExtentReport {

    static SoftAssert softAssert = new SoftAssert();
    static WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    static TestOtomasyonu testOtomasyonuPage = new TestOtomasyonu();
    static Actions actions = new Actions(Driver.getDriver());
    Faker faker = new Faker();

    @BeforeTest
    public void registerFirstSteps() throws IOException {

        // extentTest adımını başlat
        /**/extentTest = extentReports.createTest("Registration Ortak Adımları", "Kullanıcı Registration Now sayfasında olduğunu doğrular");

        //url` ye git
        extentTest.info("Kullanıcı anasayfaya gider");
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //Sayfaya gittigini dogrula
        String title = "Test Otomasyonu";
        String actTitle = Driver.getDriver().getTitle();
        softAssert.assertTrue(actTitle.contains(title), "Anasayfa Test Otomasyonu başlığını içermiyor");
        // JavaScriptExecutor'ı kullanarak sayfanın tamamen yüklenmesini bekleyin
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("return document.readyState").equals("complete");
        String dosyaYolu = ReusableMethods.tumSayfaResmi("Anasayfa");
        /**/extentTest.pass("Kullanıcı başarılı bir şekilde anasayfaya gittiğini doğrular")
                .addScreenCaptureFromPath(dosyaYolu);


        //Gorunur durumdaysa Account' a tikla
        /**/extentTest.info("Kullanıcı Account'a tıklar");
        wait.until(ExpectedConditions.elementToBeClickable(testOtomasyonuPage.account)).click();

        //Gorunur durumdaysa Sign Up' a tikla
        /**/extentTest.info("Kullanıcı Sign Up'a tıklar");
        wait.until(ExpectedConditions.elementToBeClickable(testOtomasyonuPage.signUpLink)).click();

        //Register Now sayfasina gelindigi dogrula
        /**/extentTest.info("Kullanıcı Register Now sayfasına gider");
        String formText = "Register Now";
        String actText = testOtomasyonuPage.logInFormHead.getText();
        try {
            softAssert.assertEquals(actText, formText);
            /**/extentTest.pass("Kullanıcı başarılı bir şekilde Register Now sayfasına gittiğini doğrular")
                    .addScreenCaptureFromPath(ReusableMethods.tumSayfaResmi("Register Now Page"));
        } catch (Exception e) {
            /**/extentTest.fail("Kullanıcı Register Now sayfasına gidemedi");
        }
    }

//    @AfterTest
//    public void quitDriver(){
//        ReusableMethods.bekle(1);
//        Driver.quitDriver();
//    }

    public String[] faker() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        //config reader kullanabilmesi için properties dosyasına kaydediliyor
        Properties properties = new Properties();
        File configFile = new File("configuration.properties");

        try (FileInputStream fis = new FileInputStream(configFile)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        properties.setProperty("firstName", firstName);
        properties.setProperty("lastName", lastName);
        properties.setProperty("email", email);
        properties.setProperty("password", password);

        try (FileOutputStream fos = new FileOutputStream(configFile)) {
            properties.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String[]{firstName, lastName, email, password};
    }
}
