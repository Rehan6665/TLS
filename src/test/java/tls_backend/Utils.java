package tls_backend;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Utils extends EmbraceBackendB2B {
	
	public void screenShot() throws IOException {
		 
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.moveFile(file, new File("C:\\Users\\hp\\OneDrive\\Desktop\\0_0\\Screenshots_Test\\failed.png"));
	}

}
