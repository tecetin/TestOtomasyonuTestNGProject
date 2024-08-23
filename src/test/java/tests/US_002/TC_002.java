package tests.US_002;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.Driver;
import utilities.ExtentReport;
import utilities.ReusableMethods;

import java.io.IOException;
import java.util.List;

public class TC_002 extends ExtentReport {

    //Account sayfasındaki butonların dinamik xpathi
    private String getXPathList(int i) {
        return "//li[" + i + "]/a/span";
    }

    @Test
    public void accountTest02() throws IOException {

        /*REPORT*/
        extentTest = extent.createTest("Kullanıcı Giriş Testi2",
                "Kullanıcı Log In sayfasında geçerli bilgileri girerek giriş yapılabildiğini doğrular");

        //Giriş için ortak adımlar method olarak çağrılmaktadır.
        accountBase.logIn();

        SoftAssert softAssert = new SoftAssert();

        //Kullanici profilinde My Profile butonunun gorunurlugunu test et
        //Kullanici profilinde  Wishlist butonunun gorunurlugunu test et
        //Kullanici profilinde Manage Address butonunun gorunurlugunu test et
        //Kullanici profilinde Change Password butonunun gorunurlugunu test et
        //Kullanici profilinde Logout butonunun gorunurlugunu test et

        //Her bir buton dinamik xpath ile görünürlüğü doğrulanır.
        List<String> buttons = List.of("My Profile", "My Orders", "Wishlist", "Manage Address", "Change Password", "Logout");

        for (int i = 1; i <= buttons.size(); i++) {

            WebElement button = Driver.getDriver().findElement(By.xpath(getXPathList(i)));
            softAssert.assertTrue(button.isDisplayed());
            softAssert.assertEquals(button.getText(), buttons.get(i - 1));

            /*REPORT*/
            extentTest.pass(buttons.get(i - 1) + " butonu mevcut.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(button)).build());

        }
    }
}
