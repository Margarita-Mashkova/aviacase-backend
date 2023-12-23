package com.example.aviacase;

import com.example.aviacase.model.User;
import com.example.aviacase.pages.*;
import com.example.aviacase.service.HotelService;
import com.example.aviacase.service.PurchaseService;
import com.example.aviacase.service.TourService;
import com.example.aviacase.service.UserService;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AviacaseApplicationTests {

	public static WebDriver driver;
	public static AuthPage authPage;
	public static AdminToursPage adminToursPage;
	public static AdminAddTourPage adminAddTourPage;
	public static AdminHotelsPage adminHotelsPage;
	public static AdminAddHotelPage adminAddHotelPage;
	public static AdminBindHotelsPage adminBindHotelsPage;
	public static RegistrationPage registrationPage;
	public static BuyTourPage buyTourPage;
	public static AddFeedbackPage addFeedbackPage;

	@Autowired
	private TourService tourService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private UserService userService;

	@Autowired
	private PurchaseService purchaseService;

	@Test
	@Order(1)
	void contextLoads() {
	}

	/**
	 * осуществление первоначальной настройки
	 */
	@BeforeAll
	public static void setup() {
		//определение пути до драйвера и его настройка
		System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
		//создание экземпляра драйвера
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		authPage = new AuthPage(driver);
		adminToursPage = new AdminToursPage(driver);
		adminAddTourPage = new AdminAddTourPage(driver);
		adminHotelsPage = new AdminHotelsPage(driver);
		adminAddHotelPage = new AdminAddHotelPage(driver);
		adminBindHotelsPage = new AdminBindHotelsPage(driver);
		registrationPage = new RegistrationPage(driver);
		addFeedbackPage = new AddFeedbackPage(driver);
		buyTourPage = new BuyTourPage(driver);
		//окно разворачивается на полный экран
		driver.manage().window().maximize();
		//задержка на выполнение теста = 10 сек.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	@Order(2)
	public void authAdmin() {
		driver.get(ConfProperties.getProperty("loginPage"));
		authPage.enterLogin(ConfProperties.getProperty("loginAdmin"));
		authPage.enterPassword(ConfProperties.getProperty("passwordAdmin"));
		authPage.clickBtnLogin();
	}

	@Test
	@Order(3)
	public void addTour() throws InterruptedException {
		tourService.deleteAllTours();
		Thread.sleep(300);
		driver.get(ConfProperties.getProperty("adminToursPage"));
		adminToursPage.clickBtnAddTour();
		adminAddTourPage.enterName("Тестовый тур");
		adminAddTourPage.enterCountry("Россия");
		adminAddTourPage.enterDate("2024-02-13");
		adminAddTourPage.enterPrice("2000");
		adminAddTourPage.clickBtnSaveTour();
		Assertions.assertEquals("Тестовый тур", adminToursPage.getFirstTourName());
	}

	@Test
	@Order(4)
	public void addHotel() throws InterruptedException {
		purchaseService.deleteAllPurchases();
		hotelService.deleteAllHotels();
		Thread.sleep(300);
		driver.get(ConfProperties.getProperty("adminHotelsPage"));
		adminHotelsPage.clickBtnAddHotel();
		adminAddHotelPage.enterName("Тестовый отель");
		adminAddHotelPage.enterCountry("Россия");
		adminAddHotelPage.enterCity("Сочи");
		adminAddHotelPage.enterPrice("2000");
		adminAddHotelPage.chooseThreeStars();
		adminAddHotelPage.chooseFeedYes();
		adminAddHotelPage.clickBtnSaveHotel();
		Assertions.assertEquals("Тестовый отель", adminHotelsPage.getFirstHotelName());
	}

	@Test
	@Order(5)
	public void bindHotelToTour() throws InterruptedException {
		driver.get(ConfProperties.getProperty("bindHotelsPage"));
		adminBindHotelsPage.chooseTour();
		adminBindHotelsPage.chooseHotel();
		adminBindHotelsPage.clickBtnBind();
		Thread.sleep(200);
		driver.switchTo().alert().accept();
		adminBindHotelsPage.clickBtnExit();
		driver.switchTo().alert().accept();
	}

	@Test
	@Order(6)
	public void registrationUser() throws InterruptedException {
		User deleteUser = userService.findUserByLogin(ConfProperties.getProperty("loginUser"));
		if(deleteUser != null) {
			userService.deleteUser(deleteUser.getId());
		}
		Thread.sleep(500);
		driver.get(ConfProperties.getProperty("registrationPage"));
		registrationPage.enterName("Иван");
		registrationPage.enterSurname("Иванов");
		registrationPage.enterLogin(ConfProperties.getProperty("loginUser"));
		registrationPage.enterPassword(ConfProperties.getProperty("passwordUser"));
		registrationPage.repeatPassword(ConfProperties.getProperty("passwordUser"));
		registrationPage.clickBtnRegistrate();
		Thread.sleep(700);
		User user = userService.findUserByLogin(ConfProperties.getProperty("loginUser"));
		Assertions.assertNotNull(user);
	}

	@Test
	@Order(7)
	public void authUser(){
		driver.get(ConfProperties.getProperty("loginPage"));
		authPage.enterLogin(ConfProperties.getProperty("loginUser"));
		authPage.enterPassword(ConfProperties.getProperty("passwordUser"));
		authPage.clickBtnLogin();
	}

	@Test
	@Order(8)
	public void buyTour() throws InterruptedException {
		purchaseService.deleteAllPurchases();
		Thread.sleep(700);
		driver.get(ConfProperties.getProperty("toursPage"));
		buyTourPage.clickBtnChooseTour();
		buyTourPage.clickBtnConfirmChooseTour();
		buyTourPage.clickBtnChooseHotel();
		buyTourPage.enterTourists("2");
		buyTourPage.enterNights("10");
		buyTourPage.clickBtnMakePurchase();
		buyTourPage.clickBtnBuyTour();
		driver.switchTo().alert().accept();
	}

	@Test
	@Order(9)
	public void createFeedback() throws InterruptedException {
		authUser();
		driver.get(ConfProperties.getProperty("purchasesPage"));
		addFeedbackPage.clickBtnCreateFeedback();
		addFeedbackPage.enterFeedbackText("Мне понравилось!");
		addFeedbackPage.setFourRate();
		addFeedbackPage.clickBtnSendFeedback();
		Thread.sleep(300);
		driver.switchTo().alert().accept();
		Thread.sleep(500);
		driver.get(ConfProperties.getProperty("toursPage"));
		buyTourPage.clickBtnChooseTour();
		Assertions.assertEquals("Мне понравилось!", addFeedbackPage.getFeedbackText());
	}

	@AfterAll
	public static void tearDown() {
		//Закрытие окна браузера
		driver.quit();
	}

}
