import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assert {

	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();

	@BeforeTest
	public void this_is_before_test() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.almosafer.com/en");
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void default_lasdfanguage_EN() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Almosafer: Flights, Hotels, Activities & Airlines Ticket Booking";

		softassert.assertEquals(actualTitle, expectedTitle);
		softassert.assertAll();
	}

	@Test(invocationCount = 3)
	public void change_language_randomly() {
		String[] language = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		Random rand = new Random();
		int numberOfLanguage = rand.nextInt(2);
		driver.get(language[numberOfLanguage]);

		String actualTitle = driver.getTitle();

//		System.out.println(actualTitle);

		String expectedTitleEN = "Almosafer: Flights, Hotels, Activities & Airlines Ticket Booking";
		String expectedTitleAR = "المسافر: رحلات، فنادق، أنشطة ممتعة و تذاكر طيران";

		if (numberOfLanguage == 0) {
			softassert.assertEquals(actualTitle, expectedTitleEN);
		} else {
			softassert.assertEquals(actualTitle, expectedTitleAR);

		}
		softassert.assertAll();
	}

	@Test(priority = 4, invocationCount = 15)
	public void send_keys_randomly_once_change_language() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		String[] languageUrl = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };

		String[] englishKeys = { "Dubai", "Jeddah", "Riyadh" };
		String[] arabicKeys = { "دﺑﻲ", "جدة" };

		Random rand = new Random();
		int numberOfLanguage = rand.nextInt(2);

		int numberOfenglishKeys = rand.nextInt(3);
		int numberOfArabicKeys = rand.nextInt(2);

		driver.get(languageUrl[numberOfLanguage]);

		if (numberOfLanguage == 0) {

			WebElement inputFieldEn = driver.findElement(By.xpath(
					"//*[@id=\"uncontrolled-tab-example-tabpane-flights\"]/div/div[2]/div[1]/div/div[2]/div[1]/div/div/div/input"));

			inputFieldEn.sendKeys(englishKeys[numberOfenglishKeys]);

			inputFieldEn.sendKeys(Keys.CONTROL + "a");
			inputFieldEn.sendKeys(Keys.DELETE);

		} else {
			WebElement inputFieldAr = driver.findElement(By.xpath(
					"//*[@id=\"uncontrolled-tab-example-tabpane-flights\"]/div/div[2]/div[1]/div/div[2]/div[1]/div/div/div/input"));

			inputFieldAr.sendKeys(arabicKeys[numberOfArabicKeys]);

			inputFieldAr.sendKeys(Keys.CONTROL + "a");
			inputFieldAr.sendKeys(Keys.DELETE);

		}

	}

	@Test(priority = 5)
	public void hotel_tab_is_not_selected() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		WebElement hotelTab = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tab-hotels\"]"));

		String actualResult = hotelTab.getAttribute("aria-selected");

		String expectedResult = "false";

		softassert.assertEquals(actualResult, expectedResult);
		softassert.assertAll();

	}

	@Test(priority = 6)
	public void flight_tab_is_selected() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		WebElement flightTab = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tab-flights\"]"));

		String actualValue = flightTab.getAttribute("aria-selected");

		String expectedValue = "true";

		softassert.assertEquals(actualValue, expectedValue);
		softassert.assertAll();

	}

	@Test(priority = 7)
	public void select_currancy() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.className("sc-bfYoXt")).click();
		List<WebElement> currancyMenu = driver.findElements(By.className("irihUe"));

		System.out.println("SSSIIZZEE -->" + currancyMenu.size());

		for (int i = 0; i < currancyMenu.size(); i++) {

			System.out.println(currancyMenu.get(i).getText());
			boolean is = currancyMenu.get(5).isDisplayed();

		}

	}


	@AfterTest()
	public void this_is_after_test() {

		driver.close();
	}
}
