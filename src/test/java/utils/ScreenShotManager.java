package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotManager {

    public static void takeScreenShot(WebDriver webDriver, String fileName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        File targetFile = new File("screenshots/" + fileName + "/" + dateFormat.format(date) + ".png");
//        srcFile.renameTo(targetFile);
        try {
            FileUtils.copyFile(srcFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
