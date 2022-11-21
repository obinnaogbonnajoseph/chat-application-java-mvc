package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewApplicationTests {

	@LocalServerPort
	private Integer port;

	private static WebDriver driver;

	private String baseUrl;
	private User user;

	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void afterAll() {
		driver.quit();
	}

	@BeforeEach
	public void setBaseUrl() {
		baseUrl = "http://localhost:" + port;
		user = new User(0, "obitest", "", "test", "obi", "test");
	}

	@Test
	public void signupSuccess() {
		// signup
		var signupPage = signup(user);
		// assert signup was successful
		assertTrue(signupPage.hasSuccessMsg());
	}

	@Test
	public void loginSuccess() {
		// signup
		signup(user);
		// login
		login(user.getUsername(), user.getPassword());
		assertTrue(driver.getCurrentUrl().contains("/chat"));
	}

	@Test
	public void loginFail() {
		var loginPage = login(user.getUsername(), user.getPassword());
		assertTrue(loginPage.hasErrorMsg());
	}

	@Test
	public void sendMessage() {
		// signup
		signup(user);
		// login
		login(user.getUsername(), user.getPassword());
		// chat page
		var chatPage = new ChatPage(driver);
		// send message
		chatPage.sendMessage("Hello", "Whisper");
		// assert
		assertEquals(chatPage.getUsername(), user.getUsername());
		assertEquals("hello", chatPage.getMessages().get(0));
	}

	private SignupPage signup(User user) {
		driver.get(baseUrl + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(user);
		return signupPage;
	}

	private LoginPage login(String username, String password) {
		driver.get(baseUrl + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		return loginPage;
	}

}
