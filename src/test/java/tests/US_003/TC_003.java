package tests.US_003;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TC_003 extends ExtentReport {
    SoftAssert softAssert = new SoftAssert();
    TestOtomasyonu to = new TestOtomasyonu();
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();


    @Test
    public void kategoriTesti3() throws IOException {

        /*REPORT*/
        extentTest = extent.createTest("Sayfa Kategori Testi",
                "Kullanıcı Anasayfada bulunan kategorilerin arama kutusu kategorilerinde de bulunduğunu doğrular");

        //url` ye git
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        /*REPORT*/
        extentTest.info("Kullanıcı anasayfaya gider");

        //Sayfaya gittigini dogrula
        String title = "Test Otomasyonu";
        String actTitle = Driver.getDriver().getTitle();
        softAssert.assertTrue(actTitle.contains(title));
        /*REPORT*/
        extentTest.pass("Kullanıcı başarılı bir şekilde anasayfaya gittiğini doğrular");

        //Anasayfada kategorilerin gorunurlugunu test et
        softAssert.assertFalse(to.kategoriListe.isEmpty());
        /*REPORT*/
        extentTest.pass("Kullanıcı anasayfada kategorilerin göründüğünü doğrular",
                MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64()).build());

        //Anasayfa kategori isimlerini liste olarak kaydet
        List<String> kategorilerAnasayfa = new ArrayList<>();
        int AnasayfaListSize = to.kategoriListe.size();
        for (int i = 0; i < AnasayfaListSize; i++) {
            kategorilerAnasayfa.add(i, to.kategoriListe.get(i).getText());
        }

        //Ana sayfada Most Popular Products bolumune git
        js.executeScript("arguments[0].scrollIntoView(true);", to.mostPopularProductBolumu);

        ReusableMethods.bekle(2);

        //Bolumdeki kategorilerin gorunurlugunu test et
        softAssert.assertFalse(to.mostPopularProductKategoriList.isEmpty());

        //Kategori listesindeki isimleri liste olarak kaydet
        List <String> bolumKategoriList = ReusableMethods.toStringList(to.mostPopularProductKategoriList);
        int kategoriListSize = bolumKategoriList.size();

        //Anasayfa kategori listesi ile Most Popular Products kategori listesi uzunluklarinin ayni oldugunu dogrula
        softAssert.assertEquals(AnasayfaListSize, kategoriListSize);

        //Anasayfa kategori listesindeki 'Electronics' kategorisinin Most Popular Products kategori listesinde oldugunu dogrula
        for (int i = 0; i < AnasayfaListSize; i++) {
            String kategori = kategorilerAnasayfa.get(i);
            softAssert.assertEquals(kategori, bolumKategoriList.get(i));
            /*REPORT*/
            extentTest.pass("Kullanıcı anasayfadaki " + kategori +" kategorisinin Most Popular Products listesinde de bulunmaktadır.");
        }
        extentTest.addScreenCaptureFromBase64String(ReusableMethods.sayfaSSBase64());
    }
}
