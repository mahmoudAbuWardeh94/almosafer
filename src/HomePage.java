import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomePage {
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();

	@BeforeTest
	public void this_is_before_test() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.almosafer.com/en");
		driver.manage().window().maximize();

	}

	@Test()
	public void randomly_language() {
		String[] language = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
//		System.out.println(language[0]);
//		System.out.println(language[1]);

		Random rand = new Random();
		int randomNumber = rand.nextInt(0, 3) % 2;
		driver.get(language[randomNumber]);

		String actualTitle = driver.getTitle();

//		System.out.println(actualTitle);
		if (randomNumber == 0) {
			softassert.assertEquals(actualTitle, "Almosafer: Flights, Hotels, Activities & Airlines Ticket Booking");
		} else {
			softassert.assertEquals(actualTitle, "المسافر: رحلات، فنادق، أنشطة ممتعة و تذاكر طيران");
		}

		softassert.assertAll();
	}

	@Test()
	public void check_currancy() {
		String expectedCurrancy = "SAR";
		WebElement actualCurrancy = driver
				.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/div[1]/div/button"));
		String actualCurrancyInWebsite = actualCurrancy.getText();

		softassert.assertEquals(actualCurrancyInWebsite, expectedCurrancy);
		softassert.assertAll();
	}

	@Test()
	public void Qitaf_logo() {
		WebElement qitaf = driver
				.findElement(By.xpath("//*[@id=\"__next\"]/footer/div[3]/div[3]/div[1]/div[2]/div/div[2]"));
		List<WebElement> qitafID = qitaf.findElements(By.tagName("svg"));

		String actualLogo = qitafID.get(0).getAttribute("data-testid");

		String expectedLogo = "Footer__QitafLogo";

		softassert.assertEquals(actualLogo, expectedLogo);
		softassert.assertAll();
	}

	

}
