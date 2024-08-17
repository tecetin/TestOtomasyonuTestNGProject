package tests.US_001;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;


public class TC_001 extends registrationBase{

    @Test
    public void registration(){
        //url` ye git
        //Sayfaya gittigini dogrula
        //Gorunur durumdaysa Account' a tikla
        //Gorunur durumdaysa Sign Up' a tikla
        //Register Now sayfasina gelindigi dogrula

        //Kayit sayfasinda First Name gorunurlugu test et
        Assert.assertTrue(testOtomasyonuPage.signupfirstName.isDisplayed());

        //Kayit sayfasinda Last Name gorunurlugu test et
        Assert.assertTrue(testOtomasyonuPage.signuplastName.isDisplayed());

        //Kayit sayfasinda Email gorunurlugu test et
        Assert.assertTrue(testOtomasyonuPage.signupemail.isDisplayed());

        //Kayit sayfasinda Password gorunurlugu test et
        Assert.assertTrue(testOtomasyonuPage.signuppassword.isDisplayed());

        //Kayit sayfasinda Confirm Password gorunurlugu test et
        Assert.assertTrue(testOtomasyonuPage.confirmPassword.isDisplayed());

    }
}
