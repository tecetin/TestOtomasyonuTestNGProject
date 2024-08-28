package tests.US_004;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.Driver;
import utilities.ExtentReport;
import utilities.ReusableMethods;
import utilities.TestMethods;

import java.io.IOException;
import java.util.List;

public class TC_001 extends ExtentReport {

    @Test
    public void sepeteEkleTesti() throws IOException {
        TestMethods test = new TestMethods();

        /*REPORT*/
        extentTest = extent.createTest("Sepete Ürün Ekleme",
                "Kullanıcı her kategoriden rastgele üç ürünü sepete ekler.");

        //url` ye git
        //Sayfaya gittigini dogrula
        test.sayfaDogrula();

        int kategoriAdedi = test.kategorilerLinkler.length;

        for (int i = 0; i < kategoriAdedi; i++) {

            //Tüm kategoriler için test yapılmaktadır ------->
            //Sayfada 'X' kategorisinin tiklanabilirligini test et
            //'X' kategorisine tikla
            //Sayfa url'sinin "https://testotomasyonu.com/category/X/products" oldugunu test et
            //Acilan sayfanin 'X' bolumu oldugunu test et
            test.kategoriKontroluRaporsuz(i);

            //Kategoride 0'dan fazla urun bulundugunu dogrula
            int sonucAdedi = test.kategorideUrunVarMi();

            if (sonucAdedi > 0) {
                //Rastgele verilen birinci sirada bulunan urunun listede oldugunu dogrula
                /*REPORT*/
                extentTest.info("Kullanıcı rastgele üç sayı türetecek ve ürün indeksi olarak kullanacaktır.");

                int count = Math.min(3, sonucAdedi); // Eklenmesi gereken ürün sayısı, toplam ürün sayısını aşmamalı

                //her kategoriden sepete eklenecek ürün sayisi
                List<Integer> siraSayilari = ReusableMethods.rastgeleSayilar(1, sonucAdedi, count); //eklenecek ürünlerin indeksi 1 ile toplam bulunan ürün sayısı arasında rastgele alınacaktır

                for (int j = 0; j < count; j++) {

                    int indeks = siraSayilari.get(j);

                    test.sepeteUrunEkle(indeks); //verilen ürün indeksi ile ürün sayfasına giderek sepete ekler

                    if (count > 1 && j < count - 1) { // Son eklenen üründen sonra geri gitmemesi için kontrol
                        //Bir onceki sayfaya don
                        Driver.getDriver().navigate().back();
                        /*REPORT*/
                        extentTest.info("Kullanıcı bir önceki sayfaya döner.");
                    }
                }
            }
            /*REPORT*/
            extentTest.skip("Kullanıcı bir sonraki kategoriye geçer.");
        }
        test.sepetteBulunanUrunler();

    }
}