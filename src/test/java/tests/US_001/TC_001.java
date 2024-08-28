package tests.US_001;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.ReusableMethods;

import java.io.IOException;


public class TC_001 extends registrationBase{

    @Test
    public void registration() throws IOException {
        //url` ye git
        //Sayfaya gittigini dogrula
        //Gorunur durumdaysa Account' a tikla
        //Gorunur durumdaysa Sign Up' a tikla
        //Register Now sayfasina gelindigi dogrula

        /*REPORT*/
        extentTest = extent.createTest("Registration Gorunurluk testi",
                "Kullanıcı Registration Now sayfasındaki elementlerin görünürlüğünü doğrular");

        //Kayit sayfasinda First Name gorunurlugu test et
        to = new TestOtomasyonu();

        softAssert.assertTrue(to.signupfirstName.isDisplayed());
        /*REPORT*/
        extentTest.pass("First Name elementi görünür durumdadır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.signupfirstName)).build());

        //Kayit sayfasinda Last Name gorunurlugu test et
        softAssert.assertTrue(to.signuplastName.isDisplayed());
        /*REPORT*/
        extentTest.pass("Last Name elementi görünür durumdadır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.signuplastName)).build());

        //Kayit sayfasinda Email gorunurlugu test et
        softAssert.assertTrue(to.signupemail.isDisplayed());
        /*REPORT*/
        extentTest.pass("Email elementi görünür durumdadır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.signupemail)).build());

        //Kayit sayfasinda Password gorunurlugu test et
        softAssert.assertTrue(to.signuppassword.isDisplayed());
        /*REPORT*/
        extentTest.pass("Password elementi görünür durumdadır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.signuppassword)).build());

        //Kayit sayfasinda Confirm Password gorunurlugu test et
        softAssert.assertTrue(to.confirmPassword.isDisplayed());
        /*REPORT*/
        extentTest.pass("Confirm password elementi görünür durumdadır.",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(to.confirmPassword)).build());
    }
}
