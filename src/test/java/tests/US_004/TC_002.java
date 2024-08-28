package tests.US_004;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TestOtomasyonu;
import utilities.Driver;
import utilities.ExtentReport;
import utilities.ReusableMethods;
import utilities.TestMethods;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Set;

public class TC_002 extends ExtentReport {

    boolean runValidation = true; // 3. testte kulanılmayacak kısım için koşullu çalıştırılacaktır.

    @Test
    public void urunKontrolu() throws IOException {
        SoftAssert softAssert = new SoftAssert();
        TestOtomasyonu to = new TestOtomasyonu();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        TestMethods test = new TestMethods();

        if (runValidation) {
            /*REPORT*/
            extentTest = extent.createTest("Sepete Ürün Ekleme",
                    "Kullanıcı sepete eklenen ürünler ile sayfadaki ürünlerin ayni oldugunu test eder.");
        }
        //url` ye git
        //Sayfaya gittigini dogrula
        test.sayfaDogrula();

        //Top Selling Products bolumune git
        ReusableMethods.scroll(to.topSellingBolumu);

        //View All Products butonunun tiklanabilirligini test et
        //View All Products butonuna tikla
        wait.until(ExpectedConditions.elementToBeClickable(to.viewAllP));
        ReusableMethods.click(to.viewAllP);

        //Sayfa url'sinin "https://testotomasyonu.com/trending/all-products" oldugunu test et
        test.URLTestEt("https://testotomasyonu.com/trending/all-products");

        //Kategoride 0'dan fazla urun bulundugunu dogrula
        int bulunanUrunSayisi = test.kategorideUrunVarMi();

        //Listedeki ilk urunun uzerine git
        //Urun kutusunun uzerinde dururken gorunur olan Add to Cart butonunun tiklanabilirligini test et
        //Add to cart butonuna tikla
        //Product Added To Cart! Yazisinin ciktigini dogrula
        int urunIndeksi = Math.min(bulunanUrunSayisi, 3); //ilk ürün indeksi 1den başlıyor, 3. ürün veya kategoride bulunan ürün sayısı indeksindeki ürün

        String isim = test.sepeteEkleIkonuIleUrunEkle(urunIndeksi);

        //Your Cart butonunun gorunurlugunu test et
        softAssert.assertTrue(to.cart.isDisplayed());

        //Your Cart butonunun sayisininin 1 oldugunu dogrula
        String sepetIkonundaGorunenUrunAdedi = to.cart.getText();
        softAssert.assertEquals(sepetIkonundaGorunenUrunAdedi, 1); //sepete 1 adet ürün eklenince sept ikonundaki sayının 1 olması test eediliyor.
        /*REPORT*/
        extentTest.pass("Sepete eklenen ürün sayısının, cart ikonunda da yazdığı doğrulanmıştır.");

        //Your cart butonuna tikla
        //Sayfa url'sinin "https://testotomasyonu.com/shoppingCart" oldugunu test et
        //Sepette urun bulundugunu test et
        // Sepetteki urunun isminin listede eklenen urunun ismi ile ayni oldugunu test et

        if (runValidation) {
            Map<String, Integer> sepettekiUrunler = test.sepetteBulunanUrunler();
            Set<String> sepettekiIsim = sepettekiUrunler.keySet();
            for (String each : sepettekiIsim) {
                softAssert.assertEquals(isim, each);
            }
            /*REPORT*/
            extentTest.pass("Sepete eklenen ürün ile sayfadaki ürünün ayni oldugu test edilmiştir.");
        }
    }
}