package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentRepoListners implements ITestListener{

	public ExtentReports extentReport;
	public ExtentSparkReporter spark;


	public void onStart(ITestContext context) {					

		extentReport=new ExtentReports();
		File file=new File("report.html");
		spark=new ExtentSparkReporter(file);
		spark.config().setDocumentTitle("Rest Doc");
		spark.config().setReportName("Rest Report");
		spark.config().setTimeStampFormat("DD-MM-YYYY   hh:mm:ss");

		extentReport.attachReporter(spark);

		extentReport.setSystemInfo("OS name",System.getProperty("os.name"));
		extentReport.setSystemInfo("java version",System.getProperty("java.version"));
		extentReport.setSystemInfo("API URL","https://petstore.swagger.io/v2");

	}		
	public void onTestFailure(ITestResult result) {					
		extentReport.createTest(result.getName())
		.log(Status.FAIL,result.getThrowable().getMessage());
	}		

	public void onTestSkipped(ITestResult result) {					
		extentReport.createTest(result.getName())
		.log(Status.SKIP,result.getThrowable().getMessage());

	}			

	public void onTestSuccess(ITestResult result) {					

		extentReport.createTest(result.getName()).log(Status.PASS,"Test passed");

	}	
	public void onFinish(ITestContext result) {					
		extentReport.flush();
		try {
			Desktop.getDesktop().browse(new File("report.html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}		


}
