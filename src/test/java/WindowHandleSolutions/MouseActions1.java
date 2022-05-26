package WindowHandleSolutions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.Set;

public class MouseActions1 extends WindowHandle_C02{
    //1- Yeni bir class olusturalim: MouseActions1
    //2- https://the-internet.herokuapp.com/context_menu sitesine gidelim
    //3- Cizili alan uzerinde sag click yapalim
    //4- Alert’te cikan yazinin “You selected a context menu” oldugunu
    //test edelim.
    //5- Tamam diyerek alert’i kapatalim
    //6- Elemental Selenium linkine tiklayalim
    //7- Acilan sayfada h1 taginda “Elemental Selenium” yazdigini test edelim
    WebDriver driver;
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @After
    public void tearDown() {
        //driver.close();
    }
    @Test
    public void test01() {

        //2- https://the-internet.herokuapp.com/context_menu sitesine gidelim
        driver.get("https://the-internet.herokuapp.com/context_menu");
        //3- Cizili alan uzerinde sag click yapalim
        Actions actions = new Actions(driver);
        WebElement sagTikYapilacakYer = driver.findElement(By.xpath("//div[@id='hot-spot']"));
        actions.contextClick(sagTikYapilacakYer).perform();
        //4- Alert’te cikan yazinin “You selected a context menu” oldugunu
        //test edelim.
        String expectedText = "You selected a context menu";
        String actualText = driver.switchTo().alert().getText();
        Assert.assertEquals(expectedText, actualText);

        System.out.println("murat satırları");
        driver.get("https://www.google.com");
        System.out.println("burada da bitti");

        //5- Tamam diyerek alert’i kapatalim
        driver.switchTo().alert().accept();
        //6- Elemental Selenium linkine tiklayalim
        String ilkSayfaWindowHandleDegeri = driver.getWindowHandle();
        driver.findElement(By.xpath("//*[text()='Elemental Selenium']")).click();
        //7- Acilan sayfada h1 taginda “Elemental Selenium” yazdigini test edelim
        Set<String> windowHandleseti = driver.getWindowHandles();
        System.out.println("bunu da ahmet ekledi");
        System.out.println("bu satırı da sildi");
        String ikinciSayfaWindowHandleDegeri = "";
        for (String each : windowHandleseti
        ) {
            if (!each.equals(ilkSayfaWindowHandleDegeri)) {
                ikinciSayfaWindowHandleDegeri = each;
            }
        }

        System.out.println("ahmet ekledi");
        driver.get("https://www.google.com");

        //● Amazon anasayfa adresine gidin.
        driver.get("https:www.amazon.com");
        //● Sayfa’nin window handle degerini String bir degiskene atayin
        String amazonWindowHandleDegeri = driver.getWindowHandle();
        //● Sayfa title’nin “Amazon” icerdigini test edin
        String expectedTitle = "Amazon";
        String actualTitle = driver.getTitle();

        actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle));
        //● Ilk acilan sayfaya donun ve amazon sayfasina dondugunuzu test edin
        driver.switchTo().window(amazonWindowHandleDegeri);
        //● Sayfa title’nin “Amazon” icerdigini test edin
        expectedTitle = "Amazon";
        actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle));

        Assert.assertTrue(actualTitle.contains(expectedTitle));
        //● Yeni bir tab olusturup, acilan tab’da techproeducation.com adresine gidin
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.techproeducation.com");
        //● Sayfa title’nin “TECHPROEDUCATION” icerdigini test edin
        expectedTitle = "Techpro Education";
        actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle));
        //● Yeni bir window olusturup, acilan sayfada walmart.com adresine gidin
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.walmart.com");
        //● Sayfa title’nin “Walmart” icerdigini test edin
        expectedTitle = "Walmart";
        actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle));
        //● Ilk acilan sayfaya donun ve amazon sayfasina dondugunuzu test edin
        driver.switchTo().window(amazonWindowHandleDegeri);
        //● Sayfa title’nin “Amazon” icerdigini test edin
        expectedTitle = "Amazon";
        actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle));

        System.out.println("bu satırı da sildi");
        driver.switchTo().window(ikinciSayfaWindowHandleDegeri);
        WebElement acilanSayfaTag = driver.findElement(By.xpath("//h1"));

        Assert.assertTrue(actualTitle.contains(expectedTitle));

        actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle));
        //● Ilk acilan sayfaya donun ve amazon sayfasina dondugunuzu test edin
        driver.switchTo().window(amazonWindowHandleDegeri);
        //● Sayfa title’nin “Amazon” icerdigini test edin
        expectedTitle = "Amazon";
        actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle);
    }
}
