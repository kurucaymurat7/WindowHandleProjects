package WindowHandleSolutions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WindowHandle_C03 {
    //Tests package’inda yeni bir class olusturun: WindowHandle2
    //● https://www.amazon adresine gidin.
    //● Sayfa başlığının(title) “Amazon” içerdiğini doğrulayın.
    //● yeni bir tab açarak, aşağıdaki sayfaya gidin.
    //● https://the-internet.herokuapp.com/windows adresine gidin.
    //● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
    //● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
    //● Click Here butonuna basın.
    //● Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
    //● Sayfadaki textin “New Window” olduğunu doğrulayın.
    //● Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu
    //doğrulayın.
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @After
    public void tearDown(){
        //driver.close();
    }
    @Test
    public void test01(){
        Set<String> bilinenWindowHandleDegerleri = new HashSet<>();

        //● https://www.amazon adresine gidin.
        driver.get("https://www.amazon.com");

        //amazon sayfanın window handle degeri alindi ve bir Set içerisine eklendi.
        String amazonSayfaHandleDegeri = driver.getWindowHandle();
        bilinenWindowHandleDegerleri.add(amazonSayfaHandleDegeri);

        //● Sayfa başlığının(title) “Amazon” içerdiğini doğrulayın.
        String expectedTitle = "Amazon";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle));

        //● yeni bir tab açarak, aşağıdaki sayfaya gidin.
        driver.switchTo().newWindow(WindowType.TAB);

        //● https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");

        //The Internet sayfanın window handle degeri alindi ve yukarıdakideki Sete ikinci olarak eklendi.
        String theInternetsayfaHandleDegeri = driver.getWindowHandle();
        bilinenWindowHandleDegerleri.add(theInternetsayfaHandleDegeri);

        //● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        String expectedText = "Opening a new window";
        String webElementYazi = driver.findElement(By.xpath("//h3")).getText();
        Assert.assertTrue(expectedText.equals(webElementYazi));

        //● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        expectedTitle = "The Internet";
        actualTitle = driver.getTitle();
        Assert.assertTrue(expectedTitle.equals(actualTitle));

        //● Click Here butonuna basın.
        driver.findElement(By.linkText("Click Here")).click();

        //Kontrol amacli bilinen ve bilinmeyen tum window handle degerli ekrana yazildi.
        Set<String> butunWindowHandles = driver.getWindowHandles();
        System.out.println("Acik Windows : " + butunWindowHandles);
        System.out.println("Bilinen windows : " + bilinenWindowHandleDegerleri);

        //Burada açılan yeni ppencerenin handle degerini öğrenip ona ulasmamız gerekmektedir.
        //Bilinmeyen Window HandleDegeri, acik windowslar icerisinde olan ve bilinen window set'inde olmayan elemandir.
        //Cozum, foreach ile de yapilabilir. Lambda kullanmak istedim.
        String bilinmeyenWindowHandleDegeri =
                butunWindowHandles.
                stream().
                filter(t -> (!bilinenWindowHandleDegerleri.contains(t))).
                collect(Collectors.toList()).
                get(0);

        //Bilinmeyen window handle degeri ekrana yazdirildi. Test amacli.
        System.out.println("Bilinmeyen window handle : " + bilinmeyenWindowHandleDegeri);

        //driver en yeni acilan sayfaya tasindi.
        driver.switchTo().window(bilinmeyenWindowHandleDegeri);

        //● Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
        expectedTitle = "New Window";
        actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);

        //● Sayfadaki textin “New Window” olduğunu doğrulayın.
        expectedText = "New Window";
        String actualText = driver.findElement(By.xpath("//h3")).getText();
        Assert.assertEquals(expectedText, actualText);

        //Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu doğrulayın.
        driver.switchTo().window(amazonSayfaHandleDegeri);
        expectedTitle = "The Internet";
        actualTitle = driver.getTitle();
        Assert.assertTrue(expectedTitle.equals(actualTitle));
    }
}
