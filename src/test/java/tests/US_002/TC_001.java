package tests.US_002;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ExtentReport;
import utilities.ReusableMethods;

import java.io.IOException;

public class TC_001 extends ExtentReport {

    //Dinamik xpath. Bilgiler value değeri olarak elementlerde bulunmaktadır.
    // Kaydedilen isim soyisim ve email element pathlerinde aranacaktır.
    private String bilgiXpath(String configKey) {
        String bilgi = "";
        switch (configKey) {
            case "firstName":
                bilgi = ConfigReader.getProperty("firstName");
                break;
            case "lastName":
                bilgi = ConfigReader.getProperty("lastName");
                break;
            case "email":
                bilgi = ConfigReader.getProperty("email");
                break;
        }
        return "//input[@value='" + bilgi + "']";
    }

    @Test
    public void accountTest01() throws IOException {

        /*REPORT*/
        extentTest = extent.createTest("Kullanıcı Giriş Testi1",
                "Kullanıcı Log In sayfasında geçerli bilgileri girerek giriş yapılabildiğini doğrular");

        //Giriş için ortak adımlar method olarak çağrılmaktadır.
        accountBase.logIn();

        //Kullanici First Name gorunurlugunu test et
        WebElement firstName = Driver.getDriver().findElement(By.xpath(bilgiXpath("firstName")));
        Assert.assertTrue(firstName.isDisplayed());
        /*REPORT*/
        extentTest.pass("First Name elementi görünür durumdadır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String
                        (ReusableMethods.WEResmiBase64(firstName)).build());

        //Kullanici Last Name gorunurlugunu test et
        WebElement lastName = Driver.getDriver().findElement(By.xpath(bilgiXpath("lastName")));
        Assert.assertTrue(lastName.isDisplayed());
        /*REPORT*/
        extentTest.pass("Last Name elementi görünür durumdadır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String
                        (ReusableMethods.WEResmiBase64(lastName)).build());

        //Kullanici email gorunurlugunu test et
        WebElement email = Driver.getDriver().findElement(By.xpath(bilgiXpath("email")));
        Assert.assertTrue(email.isDisplayed());
        /*REPORT*/
        extentTest.pass("Email elementi görünür durumdadır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String
                        (ReusableMethods.WEResmiBase64(email)).build());
    }
}

