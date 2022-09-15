import com.example.helpers.SeleniumInitializer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Test {

    public static void main(String[] args) {
        WebDriver driver = SeleniumInitializer.getDriver();
        driver.get("https://demoqa.com/selectable");
        WebElement firstListItem = driver.findElement(By.xpath("//li[@class='mt-2 list-group-item list-group-item-action']"));
        System.out.println(firstListItem.isEnabled());
        firstListItem.click();
        System.out.println(firstListItem.isEnabled());
    }
}
