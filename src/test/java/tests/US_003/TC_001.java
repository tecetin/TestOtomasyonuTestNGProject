package tests.US_003;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.*;

import java.io.IOException;
import java.time.Duration;

public class TC_001 extends ExtentReport {

    @Test
    public void kategoriTesti1() throws IOException {
        /*REPORT*/
        extentTest = extent.createTest("Sayfa Kategori Testi",
                "Kullanıcı Anasayfada bulunan kategorileri doğrular");

        TestMethods test = new TestMethods();

        //url` ye git
        //Sayfaya gittigini dogrula
        test.sayfaDogrula();

        //Tüm kategoriler için test yapılmaktadır ------->
            //Sayfada 'X' kategorisinin tiklanabilirligini test et
            //'X' kategorisine tikla
            //Sayfa url'sinin "https://testotomasyonu.com/category/X/products" oldugunu test et
            //Acilan sayfanin 'X' bolumu oldugunu test et
        for (int i = 0; i < test.kategorilerLinkler.length; i++) {
            test.kategoriKontroluRaporlu(i);
        }
    }
}

