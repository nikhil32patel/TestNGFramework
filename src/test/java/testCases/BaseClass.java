package testCases;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;//log4j2
import org.apache.logging.log4j.Logger;//log4j2

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties properties;

	@BeforeClass
	@Parameters({ "os", "browser" })
	void setup(String os, String br) throws IOException {

		// LOADING config.prperties file
		//FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");// ALSO VALID
		//FileReader file = new FileReader(System.getProperty("user.dir")+"/src/test/resources/config.properties");// ALSO VALID
		FileReader file = new FileReader("./src/test/resources/config.properties");
		properties = new Properties();
		properties.load(file);

		logger = LogManager.getLogger(this.getClass());

		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			if (driver != null) {
				logger.info("CHROME driver initilized");
			}
			break;
		case "edge":
			driver = new EdgeDriver();
			if (driver != null) {
				logger.info("EDGE driver initilized");
			}
			break;
		case "firefox":
			driver = new FirefoxDriver();
			if (driver != null) {
				logger.info("FIREFOX driver initilized");
			}
			break;
		default:
			System.out.println("Invalid browser name");
			return;

		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getProperty("appURL"));// READING URL FROM PROPERTIES FILE
		driver.manage().window().maximize();
	}

	@AfterClass
	void tearDown() {
		driver.quit();
	}

	public String randomString() {

		/*
		 * char[] rchar=new char[9]; if (type.equalsIgnoreCase("name")) { rchar[] = {
		 * 'W', 'S', 'G', 'H', 'B', 'N', 'D', 'T', 'Z' }; } else if
		 * (type.equalsIgnoreCase("password")) { char rchar[] = { 'N', 'b', '@', '#',
		 * '$', '(', '!', '1', '0' }; } else if (type.equalsIgnoreCase("email")) { char
		 * rchar[] = { 'N', 'B', 'T', 'J', 'A', '9', '9', '0', 'l' }; } else {
		 * System.out.println("INVALID STING TYPE"); logger.info("INVALID STRING TYPE");
		 * }
		 */
		String generatedstring = RandomStringUtils.secure().nextAlphabetic(6);
		System.out.println("GENERATED RANDOM STRING IS: " + generatedstring);
		return generatedstring.toLowerCase();

	}

}
