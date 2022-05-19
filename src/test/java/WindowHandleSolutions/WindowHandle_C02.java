package WindowHandleSolutions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WindowHandle_C02 {
    //Tests package’inda yeni bir class olusturun: WindowHandle2
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
        //● https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");
        String ilksayfaHandleDegeri = driver.getWindowHandle();

        //● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        String expectedText = "Opening a new window";
        String webElementYazi = driver.findElement(By.xpath("//h3")).getText();
        Assert.assertTrue(expectedText.equals(webElementYazi));

        //● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        String expectedTitle = "The Internet";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(expectedTitle.equals(actualTitle));

        //● Click Here butonuna basın.
        driver.findElement(By.linkText("Click Here")).click();

        //Burada açılan yeni ppencerenin handle degerini öğrenip ona ulasmamız gerekmektedir.
        Set<String> acikWindowHandles = driver.getWindowHandles();
        String ikincisayfaHandleDegeri =
                acikWindowHandles.
                stream().
                filter(t -> !t.equals(ilksayfaHandleDegeri)).
                collect(Collectors.toList()).
                get(0);

        //driver ikinci sayfaya tasindi.
        driver.switchTo().window(ikincisayfaHandleDegeri);

        //● Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
        expectedTitle = "New Window";
        actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);

        //● Sayfadaki textin “New Window” olduğunu doğrulayın.
        expectedText = "New Window";
        String actualText = driver.findElement(By.xpath("//h3")).getText();
        Assert.assertEquals(expectedText, actualText);

        //Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu doğrulayın.
        driver.switchTo().window(ilksayfaHandleDegeri);
        expectedTitle = "The Internet";
        actualTitle = driver.getTitle();
        Assert.assertTrue(expectedTitle.equals(actualTitle));
    }
}
