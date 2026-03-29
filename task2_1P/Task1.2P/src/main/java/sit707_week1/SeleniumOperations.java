package sit707_week1;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumOperations {
	
	public static void sleep(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void open_chrome_and_close() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		
		System.out.println("Fire up chrome browser.");
		WebDriver driver = new ChromeDriver();
		
		System.out.println("Driver info: " + driver);

		sleep(5);
		driver.close();
		
	}
	

	public static void open_chrome_maximize_close() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		
		System.out.println("Fire up chrome browser.");
		WebDriver driver = new ChromeDriver();
		
		System.out.println("Driver info: " + driver);
		
		sleep(5);
		
		driver.manage().window().maximize();
		
		sleep(5);
		
		driver.close();
	}

	
	public static void load_web_page_close() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		
		System.out.println("Fire up chrome browser.");
		WebDriver driver = new ChromeDriver();
		
		System.out.println("Driver info: " + driver);
		
		sleep(5);
		
		driver.get("https://selenium.dev");
		
		sleep(5);
		
		driver.close();
	}

	public static void open_chrome_loadpage_resize_close() {
		// Fill your personal information.
		System.out.println("Hello from s225101332, Prem Kumar Lingutla");
		
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		
		System.out.println("Fire up chrome browser.");
		WebDriver driver = new ChromeDriver();
		
		System.out.println("Driver info: " + driver);
		
		sleep(2);

		driver.get("https://google.com");

		sleep(2);
		driver.manage().window().setSize(new Dimension(640,480));
		sleep(2);
		driver.manage().window().setSize(new Dimension(1280,960));
		sleep(2);
		
		driver.close();
	}
}
