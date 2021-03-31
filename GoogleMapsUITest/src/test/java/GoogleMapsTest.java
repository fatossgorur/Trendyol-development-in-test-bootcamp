import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.ScreenShooter;
import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

@Listeners({ScreenShooter.class})
public class GoogleMapsTest {
    @Test
    public void findMaps() {

        Configuration.browser = "Chrome";
        Configuration.startMaximized = true;
//      Configuration.headless = true;

        open("https://www.google.com/maps");
        if ($(".widget-consent-frame").exists()) {
            switchTo().frame($(".widget-consent-frame"));
            $("#introAgreeButton").click();
        }

//      Write Istanbul in the search box and click button
        $("#searchboxinput").setValue("Istanbul");
        $("#searchbox-searchbutton").click();
//      Ankara-Istanbul map
        $(".iRxY3GoUYUY__button").click();
        $("#sb_ifc51").find("input.tactile-searchbox-input").setValue("Ankara").sendKeys(Keys.ENTER);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}