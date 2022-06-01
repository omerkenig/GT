package utilties;

import java.sql.Connection;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {

		return dr.get();
	}

	public static void setWebDriver(WebDriver driver) {

		dr.set(driver);
	}

	public static Connection getConnection(String myUrl, String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

}
