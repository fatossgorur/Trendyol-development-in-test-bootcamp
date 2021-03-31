import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class googleSearchTest {
    @Test
    public void shouldSearch() throws InterruptedException{

//        TODO
        System.setProperty("webdriver.chrome.driver", "/Users/Fatos/Downloads/chromedriver.exe");
//      launch chrome
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
//      go to www.google.com
        webDriver.get("https://www.google.com/");
        Thread.sleep(2000);
//      search for key in google search box
        webDriver.findElement(By.name("q")).sendKeys("trendyol");
        Thread.sleep(2000);
//      select "trendyol indirim kodu"
        List <WebElement> list = webDriver.findElements((By.xpath("//ul[@role='listbox']//li/descendant::div[@class='sbl1']")));
        Thread.sleep(2000);
        System.out.println(list.size());

       for (int i=0; i<list.size(); i++){

           String item = list.get(i).getText();
           System.out.println(item);

           if (item.contains("trendyol indirim kodu")){
               list.get(i).click();
               break;
           }
       }





    }
}
