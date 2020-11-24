package test;

import org.junit.Assert;
import org.junit.Before;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class OrangeHRM {
	 
	public static String browser;
	static WebDriver driver;
	
	public static void main(String[] args) throws CsvValidationException, IOException {
		OrangeHRM test = new OrangeHRM();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.setup();
		test.navigateToURL();
		test.loginFailed();
		test.login();
		test.menuEmployeeList();
//		test.searchEmployee();
//		test.resetEmployee();
		test.addEmployeePage();
		test.addEmployeeFailed();
		test.addEmployee();
//		test.editEmployeePage();
//		test.editEmployeeFailed();
//		test.editEmployee();
//		test.deleteEmployeeFailed();
//		test.deleteEmployee();
//		test.menuEmployeeRecords();
//		test.viewAttRecordsFailed();
//		test.viewAttRecords();
//		test.addAttRecordPage();
//		test.addAttRecordFailed();
//		test.addAttRecord();
//		test.editAttRecordPage();
//		test.editAttRecordFailed();
//		test.editAttRecord();
//		test.deleteAttRecordFailed();
//		test.deleteAttRecord();
//		test.menuUsers();
//		test.searchUser();
//		test.resetUser();
//		test.addUserPage();
//		test.addUserFailed();
//		test.addUser();
//		test.editUserPage();
//		test.editUserFailed();
//		test.editUser();
//		test.deleteUserFailed();
//		test.deleteUser();
//		test.menuJobtitles();
//		test.addJobPage();
//		test.addJobFailed();
//		test.addJob();
//		test.editJobPage();
//		test.editJobFailed();
//		test.editJob();
//		test.deleteJobFailed();
//		test.deleteJob();
//		test.menuPayGrades();
//		test.addPayPage();
//		test.addPayFailed();
//		test.addPay();
//		test.editPayPage();
//		test.editPayFailed();
//		test.editPay();
//		test.deletePayFailed();
//		test.deletePay();
//		test.menuEmploymentStatus();
//		test.addEmploymentPage();
//		test.addEmploymentFailed();
//		test.addEmployment();
//		test.editEmploymentPage();
//		test.editEmploymentFailed();
//		test.editEmployment();
//		test.deleteEmploymentFailed();
//		test.deleteEmployment();
//		test.menuJobCategories();
//		test.addJobCategoriesPage();
//		test.addJobCategoriesFailed();
//		test.addJobCategories();
//		test.editJobCategoriesPage();
//		test.editJobCategoriesFailed();
//		test.editJobCategory();
//		test.deleteJobCategoriesFailed();
//		test.deleteJobCategories();
//		test.menuWorkShift();
//		test.addWorkShiftPage();
//		test.addWorkShiftFailed();
//		test.addWorkShift();
//		test.editWorkShiftPage();
//		test.editWorkShiftFailed();
//		test.editWorkShift();
//		test.deleteWorkShiftFailed();
//		test.deleteWorkShift();
//		test.menuGeneralInfo();
//		test.editGeneralInfoPage();
//		test.editGeneralInfoFailed();
//		test.editGeneralInfo();
//		test.menuLocation();
//		test.searchLocation();
//		test.resetLocation();
//		test.addLocationPage();
//		test.addLocationFailed();
//		test.addLocation();
//		test.editLocationPage();
//		test.editLocationFailed();
//		test.editLocation();
//		test.deleteLocationFailed();
//		test.deleteLocation();
//		test.menuStructure();
//		test.menuSkills();
//		test.menuEducation();
//		test.menuLicense();
//		test.menuLanguage();
//		test.menuMembership();
//		test.menuNationalities();
//		test.addNationalitiesPage();
//		test.addNationalitiesFailed();
//		test.addNationalities();
//		test.editNationalitiesPage();
//		test.editNationalityFailed();
//		test.editNationality();
//		test.deleteNationalityFailed();
//		test.deleteNationality();
//		test.exit();
	}
	
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	public void setBrowserConfig() {
		String projectLocation = System.getProperty("user.dir");
		
		if(browser.contains("Chrome")) {
			System.setProperty("webdriver.chrome.driver", projectLocation + "\\lib\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		if(browser.contains("Firefox")) {
			System.setProperty("webdriver.gecko.driver", projectLocation + "\\lib\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
	}
	
	@Before
    public void setup() {
        driver.manage().window().maximize();    
    }
	
	@Test
	public void navigateToURL() {
		driver.get("https://qa.cilsy.id/symfony/web/index.php/auth/login");
		String expectedTitle = "OrangeHRM";
		String actualTitle = driver.getTitle();
		
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("Success navigate to URL");
	}

	@Test
	public void loginFailed() {
		By inputUsername = By.id("txtUsername");
		By inputPassword = By.id("txtPassword");
		By btnLogin = By.id("btnLogin");
		
		driver.findElement(inputUsername).sendKeys("Admin");
		driver.findElement(inputPassword).sendKeys("1345678");
		driver.findElement(btnLogin).click();
		
		String expectedMessage = "Invalid credentials";
		String actualMessage = driver.findElement(By.id("spanMessage")).getText();
		Assert.assertEquals(expectedMessage, actualMessage);
		System.out.println("Login failed");
	}
	
	@Test
	public void login() {
		By inputUsername = By.id("txtUsername");
		By inputPassword = By.id("txtPassword");
		By btnLogin = By.id("btnLogin");
		
		driver.findElement(inputUsername).sendKeys("Admin");
		driver.findElement(inputPassword).sendKeys("s3Kol4HQA!*");
		driver.findElement(btnLogin).click();
		
		String expectedResult = "Welcome Anfo";
		String actualResult = driver.findElement(By.id("welcome")).getText();
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Login passed");
	}
	
	@Test
	public void menuEmployeeList() {
		Actions builder = new Actions(driver);
		WebElement PIM = driver.findElement(By.id("menu_pim_viewPimModule"));
		builder.moveToElement(PIM).build().perform();
		
		By menuEmployeeList = By.id("menu_pim_viewEmployeeList");
		driver.findElement(menuEmployeeList).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Employee Information')]")).getText();
		String expectedResult = "Employee Information";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Employee List page opened");
	}
	
	@Test
	public void searchEmployee() {
		By inputSearchEmployeeName = By.id("empsearch_employee_name_empName");
		By btnSearch = By.id("searchBtn");
			
		driver.findElement(inputSearchEmployeeName).sendKeys("Zahra ");
		driver.findElement(btnSearch).click();
		String actualResult = driver.findElement(By.linkText("Zahra test")).getText();
		String expectedResult = "Zahra test";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Search employee passed");
	}
	
	@Test
	public void resetEmployee() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='resetBtn']")).click();
		
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Employee Information')]")).getText();
		String expectedResult = "Employee Information";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Reset employee passed");
	}
	
	@Test
	public void addEmployeePage() {
		driver.findElement(By.id("btnAdd")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Add Employee')]")).getText();
		String expectedResult = "Add Employee";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Employee page opened");
	}
	
	@Test
	public void addEmployeeFailed() {
		driver.findElement(By.id("lastName")).sendKeys("test");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Employee failed");
	}
	
	@Test
	public void addEmployee() {
		driver.findElement(By.id("firstName")).sendKeys("Abc1");
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("test");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Abc1 test')]")).getText();
		String expectedResult = "Abc1 test";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Employee passed");
	}
	
	@Test
	public void editEmployeePage() {
//		driver.findElement(By.linkText("Abc1 test")).click();
//		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Abc1 test')]")).getText();
//		String expectedResult = "Abc1 test";
//		Assert.assertEquals(expectedResult, actualResult);
				
		driver.findElement(By.id("btnSave")).click();
		System.out.println("Edit Employee page opened");

//		WebElement element = driver.findElement(By.id("my-id"));
//		Actions actions = new Actions(driver);
//		actions.moveToElement(element);
	}
	
	@Test
	public void editEmployeeFailed() {
		driver.findElement(By.id("personal_txtEmpFirstName")).clear();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit employee failed");
	}
	
	@Test
	public void editEmployee() {
		driver.findElement(By.id("personal_txtEmpFirstName")).sendKeys("Abc2");
		driver.findElement(By.id("personal_txtEmpMiddleName")).sendKeys("test");
		WebElement oCheckBox = driver.findElement(By.cssSelector("#personal_optGender_2"));
		oCheckBox.click();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Abc2 test')]")).getText();
		String expectedResult = "Abc2 test";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit employee passed");
	}
	
	@Test
	public void deleteEmployeeFailed() {
		By inputSearchEmployeeName = By.id("empsearch_employee_name_empName");
		By btnSearch = By.id("searchBtn");
			
		driver.findElement(inputSearchEmployeeName).sendKeys("Abc2 test");
		driver.findElement(btnSearch).click();
		
		driver.findElement(By.id("btnDelete")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Employee Information')]")).getText();
		String expectedResult = "Employee Information";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete employee failed");
	}
	
	@Test
	public void deleteEmployee() {
		WebElement oCheckBox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_171"));
		oCheckBox.click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Employee Information')]")).getText();
		String expectedResult = "Employee Information";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete employee passed");
	}
	
	@Test
	public void menuEmployeeRecords() {
		Actions builder = new Actions(driver);
		WebElement time = driver.findElement(By.id("menu_time_viewTimeModule"));
		WebElement attendence = driver.findElement(By.id("menu_attendance_Attendance"));
		builder.moveToElement(time).build().perform();
		builder.moveToElement(attendence).build().perform();
		
		By menuEmployeeRecords = By.id("menu_attendance_viewAttendanceRecord");
		driver.findElement(menuEmployeeRecords).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'View Attendance Record')]")).getText();
		String expectedResult = "View Attendance Record";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Employee Records page opened");
	}
	
	@Test
	public void viewAttRecordsFailed() {
		driver.findElement(By.id("attendance_employeeName_empName")).sendKeys("Zahra test test");
		driver.findElement(By.id("attendance_date")).click();
		driver.findElement(By.id("btView")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("View attendence records failed");
	}
	
	@Test
	public void viewAttRecords() {
		driver.findElement(By.id("attendance_employeeName_empName")).clear();
		driver.findElement(By.id("attendance_employeeName_empName")).sendKeys("Zahra test test");
		driver.findElement(By.id("attendance_date")).click();
		driver.findElement(By.id("attendance_date")).sendKeys("2020-11-18");
		driver.findElement(By.id("btView")).click();
		String actualResult = driver.findElement(By.xpath("//td[contains(text(),'Zahra test')]")).getText();
		String expectedResult = "Zahra test";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("View attendence records passed");
	}
	
	@Test
	public void addAttRecordPage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("btnPunchOut")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Punch In')]")).getText();
		String expectedResult = "Punch In";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Attendence Record page opened");
	}
	
	@Test
	public void addAttRecordFailed() {
		driver.findElement(By.id("attendance_date")).clear();
		driver.findElement(By.id("attendance_time")).sendKeys("09:00");
		driver.findElement(By.id("btnPunch")).click();
		
		String actualResult = driver.findElement(By.xpath("//span[@id='dateErrorHolder']")).getText();
		String expectedResult = "Should be a valid date in yy-mm-dd format";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add attendence record failed");
	}
	
	@Test
	public void addAttRecord() {
		driver.findElement(By.id("attendance_date")).sendKeys("2020-11-18");
		driver.findElement(By.id("attendance_time")).clear();
		driver.findElement(By.id("attendance_time")).sendKeys("10:00");
		driver.findElement(By.id("btnPunch")).click();
		
		String actualResult = driver.findElement(By.xpath("//td[contains(text(),'Zahra test')]")).getText();
		String expectedResult = "Zahra test";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add attendence record passed");
	}
	
	@Test
	public void editAttRecordPage() {
		WebElement oCheckBox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_191"));
		oCheckBox.click();
		driver.findElement(By.id("btnEdit")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Edit Attendance Records')]")).getText();
		String expectedResult = "Edit Attendance Records";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit Attendence Record page opened");
	}
	
	@Test
	public void editAttRecordFailed() {
		driver.findElement(By.id("attendance_punchInDate_1")).clear();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//div[@id='validationMsg']")).getText();
		String expectedResult = "Should be a valid date in yy-mm-dd format";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit attendence record failed");
	}
	
	@Test
	public void editAttRecord() {
		driver.findElement(By.id("attendance_punchInDate_1")).sendKeys("2020-11-18");
		driver.findElement(By.id("attendance_punchInTime_1")).click();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//td[contains(text(),'Zahra test')]")).getText();
		String expectedResult = "Zahra test";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit attendence record passed");
	}
	
	@Test
	public void deleteAttRecordFailed() {
		driver.findElement(By.id("btnDelete")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'View Attendance Record')]")).getText();
		String expectedResult = "View Attendance Record";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete attendence record failed");
	}
	
	@Test
	public void deleteAttRecord() {
		WebElement oCheckBox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_191"));
		oCheckBox.click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("okBtn")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'View Attendance Record')]")).getText();
		String expectedResult = "View Attendance Record";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete attendence record passed");
	}
	
	@Test
	public void menuUsers() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement userManagement = driver.findElement(By.id("menu_admin_UserManagement"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(userManagement).build().perform();
		
		By menuUsers = By.id("menu_admin_viewAdminModule");
		driver.findElement(menuUsers).click();
			
		String actualResult = driver.findElement(By.xpath("//*[@id=\"systemUser-information\"]/div[1]/h1")).getText();
		String expectedResult = "System Users";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("System Users page opened");
	}
	
//	@Test
//	public void searchUser() {
//		By btnSearch = By.id("searchBtn");
//		By inputSearchUserName = By.id("searchSystemUser_userName");
//		By btnReset = By.id("resetBtn");
//			
//		WebElement selectSysUsers = driver.findElement(By.id("searchSystemUser_userType"));
//		Select dropdown = new Select (selectSysUsers);
//		dropdown.selectByValue("2");
//		driver.findElement(btnSearch).click();
//		
//		driver.findElement(btnReset).click();
//		
//		driver.findElement(inputSearchUserName).sendKeys("Admin");
//		driver.findElement(btnSearch).click();
//		String actualResult3 = driver.findElement(By.linkText("Admin")).getText();
//		String expectedResult3 = "Admin";
//		Assert.assertEquals(expectedResult3, actualResult3);
//
//		driver.findElement(inputSearchUserName).clear();
//		System.out.println("Search and reset user passed");
//	}
	
	@Test
	public void searchUser() {
		By inputSearchUserName = By.id("searchSystemUser_userName");
		By btnSearch = By.id("searchBtn");
			
		driver.findElement(inputSearchUserName).sendKeys("Admin");
		driver.findElement(btnSearch).click();
		String actualResult = driver.findElement(By.linkText("Admin")).getText();
		String expectedResult = "Admin";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Search user passed");
	}
	
	@Test
	public void resetUser() {
//		By btnReset = By.id("resetBtn");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("resetBtn")).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'System Users')]")).getText();
		String expectedResult = "System Users";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Reset user passed");
	}
	
	@Test
	public void addUserPage() {
		driver.findElement(By.id("btnAdd")).click();
		String actualResult = driver.findElement(By.xpath("//*[@id=\"UserHeading\"]")).getText();
		String expectedResult = "Add User";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add User page opened");
	}
	
	@Test
	public void addUserFailed() {
		driver.findElement(By.id("systemUser_userName")).sendKeys("atest1");
		driver.findElement(By.id("systemUser_password")).sendKeys("Atest12.,");
		driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("Atest12");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Employee does not exist')]")).getText();
		String expectedResult = "Employee does not exist";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add user failed");
	}
	
	@Test
	public void addUser() {
		driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("A1 test");
		driver.findElement(By.id("systemUser_userName")).clear();
		driver.findElement(By.id("systemUser_userName")).sendKeys("abbtest11");
		driver.findElement(By.id("systemUser_password")).clear();
		driver.findElement(By.id("systemUser_password")).sendKeys("Aatest11.,/");
		driver.findElement(By.id("systemUser_confirmPassword")).clear();
		driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("Aatest11.,/");
		driver.findElement(By.id("systemUser_password")).click();
		driver.findElement(By.id("systemUser_confirmPassword")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'abbtest11')]")).getText();
		String expectedResult = "abbtest11";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add user passed");
	}
	
	@Test
	public void editUserPage() {
		driver.findElement(By.linkText("abbtest11")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='UserHeading']")).getText();
		String expectedResult = "Edit User";
		Assert.assertEquals(expectedResult, actualResult);
				
		driver.findElement(By.id("btnSave")).click();
		System.out.println("Edit User page opened");
	}
	
	@Test
	public void editUserFailed() {
		driver.findElement(By.id("systemUser_employeeName_empName")).clear();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit user failed");
	}
	
	@Test
	public void editUser() {
		driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("A1 test2");
		driver.findElement(By.id("btnSave")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'abbtest11')]")).getText();
		String expectedResult = "abbtest11";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit user passed");
	}
	
	@Test
	public void deleteUserFailed() {
		driver.findElement(By.id("btnDelete")).click();
		String actualResult = driver.findElement(By.xpath("//*[@id=\"systemUser-information\"]/div[1]/h1")).getText();
		String expectedResult = "System Users";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete user failed");
	}
	
	@Test
	public void deleteUser() {
		WebElement oCheckBox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_53"));
		oCheckBox.click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		String actualResult = driver.findElement(By.xpath("//*[@id=\"systemUser-information\"]/div[1]/h1")).getText();
		String expectedResult = "System Users";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete user passed");
	}
	
	@Test
	public void menuJobtitles() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement job = driver.findElement(By.id("menu_admin_Job"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(job).build().perform();
		
		By menuJobTitles = By.id("menu_admin_viewJobTitleList");
		driver.findElement(menuJobTitles).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Job Titles')]")).getText();
		String expectedResult = "Job Titles";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Job Titles page opened");
	}
	
	@Test
	public void addJobPage() {
		driver.findElement(By.id("btnAdd")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='saveHobTitleHeading']")).getText();
		String expectedResult = "Add Job Title";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Job Title opened");
	}
	
	@Test
	public void addJobFailed() {
		driver.findElement(By.id("jobTitle_jobDescription")).sendKeys("QA Tester");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add job failed");
	}
	
	@Test
	public void addJob() {
		driver.findElement(By.id("jobTitle_jobTitle")).sendKeys("QA Tester");
		driver.findElement(By.id("jobTitle_jobDescription")).clear();
		driver.findElement(By.id("jobTitle_jobDescription")).sendKeys("QA Tester");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'QA Tester')]")).getText();
		String expectedResult = "QA Tester";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add job passed");
	}
	
	@Test
	public void editJobPage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("QA Tester")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='saveHobTitleHeading']")).getText();
		String expectedResult = "Edit Job Title";
		Assert.assertEquals(expectedResult, actualResult);
				
		driver.findElement(By.id("btnSave")).click();
		System.out.println("Edit Job Title page opened");
	}
	
	@Test
	public void editJobFailed() {
		driver.findElement(By.id("jobTitle_jobTitle")).clear();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit job failed");
	}
	
	@Test
	public void editJob() {
		driver.findElement(By.id("jobTitle_jobTitle")).sendKeys("QA Tester");
		driver.findElement(By.id("jobTitle_jobDescription")).clear();
		driver.findElement(By.id("jobTitle_jobDescription")).sendKeys("Quality Assurance Tester");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//td[contains(text(),'Quality Assurance Tester')]")).getText();
		String expectedResult = "Quality Assurance Tester";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit job passed");
	}
	
	@Test
	public void deleteJobFailed() {
		driver.findElement(By.id("btnDelete")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Job Titles')]")).getText();
		String expectedResult = "Job Titles";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete job failed");
	}
	
	@Test
	public void deleteJob() {
		WebElement oCheckBox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_81"));
		oCheckBox.click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Job Titles')]")).getText();
		String expectedResult = "Job Titles";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete job passed");
	}
	
	@Test
	public void menuPayGrades() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement job = driver.findElement(By.id("menu_admin_Job"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(job).build().perform();
		
		By menuPayGrades = By.id("menu_admin_viewPayGrades");
		driver.findElement(menuPayGrades).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Pay Grades')]")).getText();
		String expectedResult = "Pay Grades";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Pay Grades page opened");
	}
	
	@Test
	public void addPayPage() {
		driver.findElement(By.id("btnAdd")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='payGradeHeading']")).getText();
		String expectedResult = "Add Pay Grade";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Pay Grade opened");
	}
	
	@Test
	public void addPayFailed() {
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add pay grade failed");
	}
	
	@Test
	public void addPay() {
		driver.findElement(By.id("payGrade_name")).sendKeys("Staff1");
		driver.findElement(By.id("btnSave")).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("btnAddCurrency")).click();
		
		driver.findElement(By.id("payGradeCurrency_currencyName")).sendKeys("IDR - Indonesian Rupiah");
		driver.findElement(By.id("payGradeCurrency_minSalary")).sendKeys("1000");
		driver.findElement(By.id("payGradeCurrency_maxSalary")).sendKeys("2000");
		driver.findElement(By.id("btnSaveCurrency")).click();
		
		driver.findElement(By.id("btnCancel")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'Staff1')]")).getText();
		String expectedResult = "Staff1";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add pay grade passed");
	}
	
	@Test
	public void editPayPage() {
		driver.findElement(By.linkText("Staff1")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='payGradeHeading']")).getText();
		String expectedResult = "Edit Pay Grade";
		Assert.assertEquals(expectedResult, actualResult);
				
		driver.findElement(By.id("btnSave")).click();
		System.out.println("Edit Pay Grade page opened");
	}
	
	@Test
	public void editPayFailed() {
		driver.findElement(By.id("payGrade_name")).clear();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit pay grade failed");
	}
	
	@Test
	public void editPay() {
		driver.findElement(By.id("payGrade_name")).sendKeys("Staff1");
		driver.findElement(By.id("btnSave")).click();
		
		driver.findElement(By.id("btnCancel")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'Staff1')]")).getText();
		String expectedResult = "Staff1";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit pay grade passed");
	}
	
	@Test
	public void deletePayFailed() {
		driver.findElement(By.id("btnDelete")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Pay Grades')]")).getText();
		String expectedResult = "Pay Grades";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete pay grade failed");
	}
	
	@Test
	public void deletePay() {
		WebElement oCheckBox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_74"));
		oCheckBox.click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Pay Grades')]")).getText();
		String expectedResult = "Pay Grades";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete pay grade passed");
	}
	
	@Test
	public void menuEmploymentStatus() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement job = driver.findElement(By.id("menu_admin_Job"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(job).build().perform();
		
		By menuEmploymentStatus = By.id("menu_admin_employmentStatus");
		driver.findElement(menuEmploymentStatus).click();
			
		String actualResult = driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[1]/h1")).getText();
		String expectedResult = "Employment Status";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Employment Status page opened");
	}
	
	@Test
	public void addEmploymentPage() {
		driver.findElement(By.id("btnAdd")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='empStatusHeading']")).getText();
		String expectedResult = "Add Employment Status";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Employment Status opened");
	}
	
	@Test
	public void addEmploymentFailed() {
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add employment status failed");
	}
	
	@Test
	public void addEmployment() {
		driver.findElement(By.id("empStatus_name")).sendKeys("Internship1");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'Internship1')]")).getText();
		String expectedResult = "Internship1";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add employment status passed");
	}
	
	@Test
	public void editEmploymentPage() {
		driver.findElement(By.linkText("Internship1")).click();
//		String actualResult = driver.findElement(By.xpath("//h1[@id='empStatusHeading']")).getText();
//		String expectedResult = "Edit Employment Status";
//		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit Employment Status page opened");
	}
	
	@Test
	public void editEmploymentFailed() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("empStatus_name")).clear();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit employment status failed");
	}
	
	@Test
	public void editEmployment() {
		driver.findElement(By.id("empStatus_name")).sendKeys("Internship1");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'Internship1')]")).getText();
		String expectedResult = "Internship1";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit employment status passed");
	}
	
	@Test
	public void deleteEmploymentFailed() {
		driver.findElement(By.id("btnDelete")).click();
		String actualResult = driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[1]/h1")).getText();
		String expectedResult = "Employment Status";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete employment status failed");
	}
	
	@Test
	public void deleteEmployment() {
		WebElement oCheckBox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_71"));
		oCheckBox.click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		String actualResult = driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[1]/h1")).getText();
		String expectedResult = "Employment Status";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete employment status passed");
	}
	
	@Test
	public void menuJobCategories() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement job = driver.findElement(By.id("menu_admin_Job"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(job).build().perform();
		
		By menuJobCategories = By.id("menu_admin_jobCategory");
		driver.findElement(menuJobCategories).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Job Categories')]")).getText();
		String expectedResult = "Job Categories";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Job Categories page opened");
	}
	
	@Test
	public void addJobCategoriesPage() {
		driver.findElement(By.id("btnAdd")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='jobCategoryHeading']")).getText();
		String expectedResult = "Add Job Category";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Job Category page opened");
	}
	
	@Test
	public void addJobCategoriesFailed() {
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add job category failed");
	}
	
	@Test
	public void addJobCategories() {
		driver.findElement(By.id("jobCategory_name")).sendKeys("AQA Tester");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'AQA Tester')]")).getText();
		String expectedResult = "AQA Tester";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add job category passed");
	}
	
	@Test
	public void editJobCategoriesPage() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(),'AQA Tester')]")).click();
//		String actualResult = driver.findElement(By.xpath("//h1[@id='empStatusHeading']")).getText();
//		String expectedResult = "Edit Employment Status";
//		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit Job Categories page opened");
	}
	
	@Test
	public void editJobCategoriesFailed() {
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("jobCategory_name")).clear();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit job category failed");
	}
	
	@Test
	public void editJobCategory() {
		driver.findElement(By.id("jobCategory_name")).sendKeys("AQA Tester");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'AQA Tester')]")).getText();
		String expectedResult = "AQA Tester";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit job category passed");
	}
	
	@Test
	public void deleteJobCategoriesFailed() {
		driver.findElement(By.id("btnDelete")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Job Categories')]")).getText();
		String expectedResult = "Job Categories";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete job category failed");
	}
	
	@Test
	public void deleteJobCategories() {
		WebElement oCheckBox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_65"));
		oCheckBox.click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Job Categories')]")).getText();
		String expectedResult = "Job Categories";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete job category passed");
	}
	
	@Test
	public void menuWorkShift() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement job = driver.findElement(By.id("menu_admin_Job"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(job).build().perform();
		
		By menuWorkShift = By.id("menu_admin_workShift");
		driver.findElement(menuWorkShift).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Work Shifts')]")).getText();
		String expectedResult = "Work Shifts";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Work Shifts page opened");
	}
	
	@Test
	public void addWorkShiftPage() {
		driver.findElement(By.id("btnAdd")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='workShiftHeading']")).getText();
		String expectedResult = "Add Work Shift";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Work Shift page opened");
	}
	
	@Test
	public void addWorkShiftFailed() {
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add work Shift failed");
	}
	
	@Test
	public void addWorkShift() {
		driver.findElement(By.id("workShift_name")).sendKeys("Alembur");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'Alembur')]")).getText();
		String expectedResult = "Alembur";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add work shift passed");
	}
	
	@Test
	public void editWorkShiftPage() {
		driver.findElement(By.xpath("//a[contains(text(),'Alembur')]")).click();
//		String actualResult = driver.findElement(By.xpath("//h1[@id='workShiftHeading']")).getText();
//		String expectedResult = "Edit Work Shift";
//		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit Work Shift page opened");
	}
	
	@Test
	public void editWorkShiftFailed() {
		driver.findElement(By.id("workShift_name")).click();
		driver.findElement(By.xpath("//input[@id='workShift_name']")).clear();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit work shift failed");
	}
	
	@Test
	public void editWorkShift() {
		driver.findElement(By.id("workShift_name")).sendKeys("Alembur");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'Alembur')]")).getText();
		String expectedResult = "Alembur";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit work shift passed");
	}
	
	@Test
	public void deleteWorkShiftFailed() {
		driver.findElement(By.id("btnDelete")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Work Shifts')]")).getText();
		String expectedResult = "Work Shifts";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete work shift failed");
	}
	
	@Test
	public void deleteWorkShift() {
		WebElement oCheckBox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_53"));
		oCheckBox.click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Work Shifts')]")).getText();
		String expectedResult = "Work Shifts";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete work shift passed");
	}
	
	@Test
	public void menuGeneralInfo() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement organization = driver.findElement(By.id("menu_admin_Organization"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(organization).build().perform();
		
		By menuGeneralInfo = By.id("menu_admin_viewOrganizationGeneralInformation");
		driver.findElement(menuGeneralInfo).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[@id='genInfoHeading']")).getText();
		String expectedResult = "General Information";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("General Information page opened");
	}
	
	@Test
	public void editGeneralInfoPage() {
		driver.findElement(By.id("btnSaveGenInfo")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='genInfoHeading']")).getText();
		String expectedResult = "General Information";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit General Information page opened");
	}
	
	@Test
	public void editGeneralInfoFailed() {
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("organization_name")).clear();
		driver.findElement(By.id("organization_phone")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("btnSaveGenInfo")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit General Information failed");
	}
	
	@Test
	public void editGeneralInfo() {
		driver.findElement(By.id("organization_name")).sendKeys("Ajo Hotel");
		driver.findElement(By.id("btnSaveGenInfo")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='genInfoHeading']")).getText();
		String expectedResult = "General Information";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit General Information passed");
	}
	
	@Test
	public void menuLocation() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement organization = driver.findElement(By.id("menu_admin_Organization"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(organization).build().perform();
		
		By menuLocation = By.id("menu_admin_viewLocations");
		driver.findElement(menuLocation).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[@id='searchLocationHeading']")).getText();
		String expectedResult = "Locations";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Locations page opened");
	}
	
	@Test
	public void searchLocation() {
		By inputSearchLocationName = By.id("searchLocation_name");
		By btnSearch = By.id("btnSearch");
			
		driver.findElement(inputSearchLocationName).sendKeys("Pusat");
		driver.findElement(btnSearch).click();
		String actualResult = driver.findElement(By.linkText("Pusat")).getText();
		String expectedResult = "Pusat";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Search Location passed");
	}
	
	@Test
	public void resetLocation() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("btnReset")).click();
		
		String actualResult = driver.findElement(By.xpath("//h1[@id='searchLocationHeading']")).getText();
		String expectedResult = "Locations";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Reset Location passed");
	}
	
	@Test
	public void addLocationPage() {
		driver.findElement(By.id("btnAdd")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='locationHeading']")).getText();
		String expectedResult = "Add Location";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Location page opened");
	}
	
	@Test
	public void addLocationFailed() {
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Location failed");
	}
	
	@Test
	public void addLocation() {
		driver.findElement(By.id("location_name")).sendKeys("Cabang A");
		WebElement selectCountry = driver.findElement(By.id("location_country"));
		Select dropdown = new Select (selectCountry);
		dropdown.selectByValue("AF");
		
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'Cabang A')]")).getText();
		String expectedResult = "Cabang A";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Location passed");
	}
	
	@Test
	public void editLocationPage() {
		driver.findElement(By.xpath("//a[contains(text(),'Cabang A')]")).click();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='locationHeading']")).getText();
		String expectedResult = "Edit Location";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit Location page opened");
	}
	
	@Test
	public void editLocationFailed() {
		driver.findElement(By.id("location_name")).clear();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit Location failed");
	}
	
	@Test
	public void editLocation() {
		driver.findElement(By.id("location_name")).sendKeys("Cabang A");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'Cabang A')]")).getText();
		String expectedResult = "Cabang A";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit Location passed");
	}
	
	@Test
	public void deleteLocationFailed() {
		driver.findElement(By.id("btnDelete")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='searchLocationHeading']")).getText();
		String expectedResult = "Locations";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete Location failed");
	}
	
	@Test
	public void deleteLocation() {
		WebElement oCheckBox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_48"));
		oCheckBox.click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='searchLocationHeading']")).getText();
		String expectedResult = "Locations";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete Location passed");
	}
	
	@Test
	public void menuStructure() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement organization = driver.findElement(By.id("menu_admin_Organization"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(organization).build().perform();
		
		By menuStructure = By.id("menu_admin_viewCompanyStructure");
		driver.findElement(menuStructure).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Organization Structure')]")).getText();
		String expectedResult = "Organization Structure";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Organization Structure page opened");
	}
	
	@Test
	public void menuSkills() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement qualification = driver.findElement(By.id("menu_admin_Qualifications"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(qualification).build().perform();
		
		By menuSkills = By.id("menu_admin_viewSkills");
		driver.findElement(menuSkills).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Skills')]")).getText();
		String expectedResult = "Skills";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Skills page opened");
	}
	
	@Test
	public void menuEducation() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement qualification = driver.findElement(By.id("menu_admin_Qualifications"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(qualification).build().perform();
		
		By menuEducation = By.id("menu_admin_viewEducation");
		driver.findElement(menuEducation).click();
			
		String actualResult = driver.findElement(By.xpath("//body/div[@id='wrapper']/div[@id='content']/div[@id='recordsListDiv']/div[1]/h1[1]")).getText();
		String expectedResult = "Education";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Education page opened");
	}
	
	@Test
	public void menuLicense() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement qualification = driver.findElement(By.id("menu_admin_Qualifications"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(qualification).build().perform();
		
		By menuLicense = By.id("menu_admin_viewLicenses");
		driver.findElement(menuLicense).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Licenses')]")).getText();
		String expectedResult = "Licenses";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Licenses page opened");
	}
	
	@Test
	public void menuLanguage() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement qualification = driver.findElement(By.id("menu_admin_Qualifications"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(qualification).build().perform();
		
		By menuLanguage = By.id("menu_admin_viewLanguages");
		driver.findElement(menuLanguage).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Languages')]")).getText();
		String expectedResult = "Languages";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Languages page opened");
	}
	
	@Test
	public void menuMembership() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		WebElement qualification = driver.findElement(By.id("menu_admin_Qualifications"));
		builder.moveToElement(admin).build().perform();
		builder.moveToElement(qualification).build().perform();
		
		By menuMembership = By.id("menu_admin_membership");
		driver.findElement(menuMembership).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Memberships')]")).getText();
		String expectedResult = "Memberships";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Memberships page opened");
	}
	
	@Test
	public void menuNationalities() {
		Actions builder = new Actions(driver);
		WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
		builder.moveToElement(admin).build().perform();
		
		By menuNationalities = By.id("menu_admin_nationality");
		driver.findElement(menuNationalities).click();
			
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Nationalities')]")).getText();
		String expectedResult = "Nationalities";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Nationalities page opened");
	}
	
	@Test
	public void addNationalitiesPage() {
		driver.findElement(By.id("btnAdd")).click();
		String actualResult = driver.findElement(By.xpath("//h1[@id='nationalityHeading']")).getText();
		String expectedResult = "Add Nationality";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Nationality Status opened");
	}
	
	@Test
	public void addNationalitiesFailed() {
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Nationality failed");
	}
	
	@Test
	public void addNationalities() {
		driver.findElement(By.id("nationality_name")).sendKeys("Aaindo");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'Aaindo')]")).getText();
		String expectedResult = "Aaindo";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Add Nationality passed");
	}
	
	@Test
	public void editNationalitiesPage() {
		driver.findElement(By.linkText("Aaindo")).click();
//		String actualResult = driver.findElement(By.xpath("//h1[@id='nationalityHeading']")).getText();
//		String expectedResult = "Edit Nationality";
//		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit Nationality page opened");
	}
	
	@Test
	public void editNationalityFailed() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("nationality_name")).clear();
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		String expectedResult = "Required";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit Nationality failed");
	}
	
	@Test
	public void editNationality() {
		driver.findElement(By.id("nationality_name")).sendKeys("Aaindo");
		driver.findElement(By.id("btnSave")).click();
		String actualResult = driver.findElement(By.xpath("//a[contains(text(),'Aaindo')]")).getText();
		String expectedResult = "Aaindo";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Edit Nationality passed");
	}
	
	@Test
	public void deleteNationalityFailed() {
		driver.findElement(By.id("btnDelete")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Nationalities')]")).getText();
		String expectedResult = "Nationalities";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete Nationality failed");
	}
	
	@Test
	public void deleteNationality() {
		WebElement oCheckBox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_216"));
		oCheckBox.click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		String actualResult = driver.findElement(By.xpath("//h1[contains(text(),'Nationalities')]")).getText();
		String expectedResult = "Nationalities";
		Assert.assertEquals(expectedResult, actualResult);
		System.out.println("Delete Location passed");
	}
	
	@After
    public void exit() {
        driver.close();
        driver.quit();
    }
	
	
}
