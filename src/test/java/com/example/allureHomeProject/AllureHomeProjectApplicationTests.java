package com.example.allureHomeProject;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.junit.Assert.assertEquals;

@SpringBootTest
class AllureHomeProjectApplicationTests {

	private static final String EXPECTED_OTUS_CONTACTS_TEXT = "125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02";
	private static final String OTUS_WEBSITE_HOME_PAGE = "https://otus.ru";

	@FindBy(xpath = "//header//a[@href='/contacts/']")
	private WebElement contactsButton;


	private Logger logger = LogManager.getLogger(AllureHomeProjectApplicationTests.class);
	protected WebDriver driver;


	@Test
	@Step("Check Title")
	@Epic("OTUS")
	@Feature("GET")
	@Story("Check Title from www.otus.ru")
	@Description("Check Title from www.otus.ru")
	public void checkTitle() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger.info("Драйвер поднят");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get(OTUS_WEBSITE_HOME_PAGE);
		logger.info("Сайт открыт");
		logger.info("Проверяем title страницы");
		String actualTitle = driver.getTitle();
		String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
		assertEquals("Текст тайтла не соответствует ожидаемому", expectedTitle, actualTitle);
		if (driver!=null)
			driver.quit();
	}

	@Test
	@Step("Check Title Contacts")
	@Epic("OTUS")
	@Feature("GET")
	@Story("Check Title Contacts from www.otus.ru")
	@Description("Check Title Contacts from www.otus.ru")
	public void checkTitleContacts() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger.info("Драйвер поднят");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get(OTUS_WEBSITE_HOME_PAGE);
		logger.info("Сайт открыт");
		PageFactory.initElements(driver, this);
		contactsButton.click();
		//driver.findElement(By.xpath("//header//a[@href='/contacts/']")).click();
		logger.info("Открыта страница Контакты");
		logger.info("Проверяем title страницы Контакты");
		String actualTitle = driver.getTitle();
		String expectedTitle = "Контакты | OTUS";
		assertEquals("Текст тайтла страницы Контакты не соответствует ожидаемому", expectedTitle, actualTitle);

		if (driver!=null)
			driver.quit();
	}


	@Test
	@Step("Check Address In Contacts")
	@Epic("OTUS")
	@Feature("GET")
	@Story("Check  Address In Contacts from www.otus.ru")
	@Description("Check  Address In Contacts from www.otus.ru")
	public void checkAdressInContacts() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger.info("Драйвер поднят");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get("https://otus.ru/contacts");
		logger.info("Открыта страница Контакты");
		Allure.addAttachment("Cтраница Контакты", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		assertEquals( "Адрес не соответствует ожидаемому",EXPECTED_OTUS_CONTACTS_TEXT, driver.findElement(By.xpath("//div[text()='Адрес']/../div[2]")).getText());

		if (driver!=null)
			driver.quit();
	}

	@Test
	@Step("Check Open MaxScreen")
	@Epic("OTUS")
	@Feature("GET")
	@Story("Check Open MaxScreen from www.otus.ru")
	@Description("Check Open MaxScreen from www.otus.ru")
	public void checkOpenMaxScreen() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger.info("Драйвер поднят");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get(OTUS_WEBSITE_HOME_PAGE);
		driver.manage().window().maximize();
		logger.info("Открыто окно браузера на полный экран");
		logger.info(driver.manage().window().getSize());

		if (driver!=null)
			driver.quit();
	}

	@Test
	@Step("Check FAQ")
	@Epic("OTUS")
	@Feature("GET")
	@Story("Check FAQ from www.otus.ru")
	@Description("Check FAQ from www.otus.ru")
	public void checkFAQ() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger.info("Драйвер поднят");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get(OTUS_WEBSITE_HOME_PAGE);
		logger.info("Сайт открыт");
		driver.findElement(By.xpath("//header//a[@href='/faq/']")).click();
		logger.info("Открыта страница FAQ");
		driver.findElement(By.cssSelector("body > div.body-wrapper > div > div.container.container-padding-bottom.js-faq > div.container__row.js-tabs > div:nth-child(2) > div:nth-child(4) > div.faq-question__question.js-faq-question-question")).click();
		String expectedProgram = "Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”";
		Allure.addAttachment("Cтраница FAQ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		assertEquals("Текст не соответствует ожидаемому", expectedProgram, driver.findElement(By.cssSelector("body > div.body-wrapper > div > div.container.container-padding-bottom.js-faq > div.container__row.js-tabs > div:nth-child(2) > div:nth-child(4) > div.faq-question__answer.js-faq-answer")).getText());
		logger.info("Текст соответствует ожидаемому");

		if (driver!=null)
			driver.quit();
	}

	@Test
	@Step("Check Subscription")
	@Epic("OTUS")
	@Feature("GET")
	@Story("Check Subscription from www.otus.ru")
	@Description("Check Subscription from www.otus.ru")
	public void checkSubscription() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger.info("Драйвер поднят");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get(OTUS_WEBSITE_HOME_PAGE);
		logger.info("Сайт открыт");
		driver.findElement(By.cssSelector(".input.footer2__subscribe-input")).clear();
		logger.info("Поле email очищено");
		driver.findElement(By.cssSelector(".input.footer2__subscribe-input")).sendKeys("test@test.com");
		driver.findElement(By.cssSelector(".footer2__subscribe-button.button.button_blue.button_as-input")).click();
		logger.info("Нажата кнопка Подписаться");
		Allure.addAttachment("Нажата кнопка Подписаться", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		assertEquals( "Вы успешно подписались", driver.findElement(By.cssSelector("body > div.body-wrapper > div > footer > div > div > div.footer2__content > div > div:nth-child(3) > p.subscribe-modal__success")).getText());
		logger.info("Текст Вы успешно подписались");

		if (driver!=null)
			driver.quit();
	}


}
