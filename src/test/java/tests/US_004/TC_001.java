package tests.US_004;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.Driver;
import utilities.ExtentReport;
import utilities.ReusableMethods;
import utilities.TestMethods;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TC_001 extends ExtentReport {
    SoftAssert softAssert = new SoftAssert();
    TestOtomasyonu to = new TestOtomasyonu();
    Actions actions = new Actions(Driver.getDriver());
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    private String urunXPath(int i) {
        return "//section[2]/div[2]/div/div/ul/li[" + i + "]/div/a";
    }

    @Test
    public void sepeteEkleTesti() throws IOException {
        TestMethods test = new TestMethods();
        List<String> urunIsimleri = new ArrayList<>();

        /*REPORT*/
        extentTest = extent.createTest("Sepete Ürün Ekleme",
                "Kullanıcı her kategoriden rastgele üç ürünü sepete ekler.");

        //url` ye git
        //Sayfaya gittigini dogrula
        test.sayfaDogrula();

        for (int i = 0; i < test.kategorilerLinkler.length; i++) {

            //Tüm kategoriler için test yapılmaktadır ------->
            //Sayfada 'X' kategorisinin tiklanabilirligini test et
            //'X' kategorisine tikla
            //Sayfa url'sinin "https://testotomasyonu.com/category/X/products" oldugunu test et
            //Acilan sayfanin 'X' bolumu oldugunu test et
            test.kategoriKontroluRaporsuz(i);

            //Kategoride 0'dan fazla urun bulundugunu dogrula
            wait.until(ExpectedConditions.visibilityOf(to.sonuc));
            int sonucAdedi = Integer.parseInt(to.sonuc.getText().replaceAll("\\D", ""));
            try {
                softAssert.assertTrue(sonucAdedi > 0);
                /*REPORT*/
                extentTest.info("Kategori içerisinde ürün bulunmaktadır.");

                //Rastgele verilen birinci sirada bulunan urunun listede oldugunu dogrula
                /*REPORT*/
                extentTest.info("Kullanıcı rastgele üç sayı türetecek ve ürün indeksi olarak kullanacaktır.");
                List<Integer> siraSayilari = ReusableMethods.rastgeleSayilar(1, sonucAdedi, 3);

                for (int j = 0; j < siraSayilari.size(); j++) {

                    int sira = siraSayilari.get(j);

                    WebElement urun = Driver.getDriver().findElement(By.xpath(urunXPath(sira)));
                    wait.until(ExpectedConditions.visibilityOf(urun));
                    Assert.assertTrue(urun.isDisplayed());
                    /*REPORT*/
                    extentTest.info("Kullanıcı " + sira + ". ürünü seçmiştir.",
                            MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.WEResmiBase64(urun)).build());

                    //Js ile ürüne scroll ile gidip tıklanır
                    ReusableMethods.scroll(urun);
                    ReusableMethods.click(urun);

                    //Sayfa url'sinin https://testotomasyonu.com/product/" icerdigini test et
                    String expUrl = "https://testotomasyonu.com/product/";
                    String actUrl = Driver.getDriver().getCurrentUrl();
                    wait.until(ExpectedConditions.visibilityOf(to.addToCart));
                    Assert.assertTrue(actUrl.contains(expUrl));
                    /*REPORT*/
                    extentTest.pass("Kullanıcı ürüne tıkladığında ürün sayfası açılmıştır",
                            MediaEntityBuilder.createScreenCaptureFromBase64String
                                    (ReusableMethods.sayfaSSBase64()).build());

                    //urun Ismi kaydedilir
                    String isim = to.urunIsmi.getText();
                    urunIsimleri.add(isim);

                    //Add to cart butonuna tikla
                    ReusableMethods.click(to.addToCart);
                    /*REPORT*/
                    extentTest.info("Kullanıcı sepete ekle butonuna tıklar.");

                    //Product Added To Cart! Yazisinin ciktigini dogrula
                    softAssert.assertTrue(to.addedMessage.isDisplayed());
                    /*REPORT*/
                    extentTest.pass("Ürün sepete eklenmiştir.",
                            MediaEntityBuilder.createScreenCaptureFromBase64String
                                    (ReusableMethods.WEResmiBase64(to.addedMessage)).build());

                    //Bir onceki sayfaya don
                    Driver.getDriver().navigate().back();
                    /*REPORT*/
                    extentTest.info("Kullanıcı bir önceki sayfaya döner.");
                }
                /*REPORT*/
                extentTest.skip("Kullanıcı bir sonraki kategoriye geçer.");
            } catch (Exception e) {
                /*REPORT*/
                extentTest.fail("Kategori içerisinde ürün bulunmamaktadır.");
            }
        }
    }
}