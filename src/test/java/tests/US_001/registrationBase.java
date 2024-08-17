package tests.US_001;

import com.github.javafaker.Faker;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class registrationBase {

    static SoftAssert softAssert = new SoftAssert();
    static WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    static TestOtomasyonu testOtomasyonuPage = new TestOtomasyonu();
    static Actions actions = new Actions(Driver.getDriver());
    Faker faker = new Faker();

    @BeforeTest
    public void registerFirstSteps() {

        //url` ye git
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //Sayfaya gittigini dogrula
        String title = "Test Otomasyonu";
        String actTitle = Driver.getDriver().getTitle();
        softAssert.assertTrue(actTitle.contains(title));

        //Gorunur durumdaysa Account' a tikla
        wait.until(ExpectedConditions.elementToBeClickable(testOtomasyonuPage.account)).click();

        //Gorunur durumdaysa Sign Up' a tikla
        wait.until(ExpectedConditions.elementToBeClickable(testOtomasyonuPage.signUpLink)).click();

        //Register Now sayfasina gelindigi dogrula
        String formText = "Register Now";
        String actText = testOtomasyonuPage.logInFormHead.getText();
        softAssert.assertEquals(actText, formText);
    }

    @AfterTest
    public void quitDriver(){
        ReusableMethods.bekle(1);
        Driver.quitDriver();
    }

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
