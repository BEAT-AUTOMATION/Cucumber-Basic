package StepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import resources.base;

import java.util.List;
import java.util.Map;


public class MyStepdefs extends base {

    WebDriver driver;

    public MyStepdefs() {
        this.driver = base.driver;
    }

//    @Given("Initialize the browser")
//    public void initializeTheBrowser() throws IOException {
//     driver = initializeDriver();
//    }

    @Given("Open new user registration page")
    public void OpenNewUserRegistrationPage() throws InterruptedException {
        Thread.sleep(5000);
        WebElement ele = driver.findElement(By.xpath("//button[contains(text(),'New User')]"));
        ewait().until(ExpectedConditions.elementToBeClickable(ele));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);

    }


    @And("Go to Book Store")
    public void goToBookStore()  {
        WebElement ele = driver.findElement(By.xpath("//*[@class='avatar mx-auto white']/*[name()='svg']//..//following::div//h5[contains(text(),'Book')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);

    }

    @And("user clicks on Login Page")
    public void userClicksOnLoginPage() {
        WebElement ele = driver.findElement(By.xpath("//*[@class='btn btn-light ']/*[name()='svg']//..//span[contains(text(),'Login')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
    }

    @When("user enters the below data for new user")
    public void userEntersTheBelowData(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        driver.findElement(By.id("firstname")).sendKeys(data.get(0).get("FirstName"));
        driver.findElement(By.id("lastname")).sendKeys(data.get(0).get("LastName"));
        driver.findElement(By.id("userName")).sendKeys(data.get(0).get("UserName"));
        driver.findElement(By.id("password")).sendKeys(data.get(0).get("Password"));

    }


    @And("user clicks on Register button")
    public void userClicksOnRegisterButton() {
        WebElement ele = driver.findElement(By.xpath("//button[contains(text(),'Register')]"));
        ewait().until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();

    }

    @And("User solves the reCaptcha box")
    public void userSolvesTheReCaptchaBox() throws InterruptedException {
        base.ReCaptcha_click();
        Thread.sleep(15000);
    }

//    @And("Verify user is created successfully")
//    public void verifyUserIsCreatedSuccessfully() throws InterruptedException {
//        Thread.sleep(3000);

//        driver.switchTo().alert();
//        String successMessage = driver.switchTo().alert().getText();
//        Assert.assertEquals("Verify new user Success Message",successMessage,"User Register Successfully.");
//        driver.switchTo().alert().accept();
//
//    }

    @And("User verifies login is success")
    public void userVerifiesLoginIsSuccess(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String userNameVerify = driver.findElement(By.xpath("//*[@id='userName-value']")).getText();
        String dataFromTable = data.get(0).get("UserName");
        if (userNameVerify.equalsIgnoreCase(dataFromTable)) {
            Assert.assertTrue(true);
        }

    }

    @When("user enters the below data to login")
    public void userEntersTheBelowDataToLogin(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        driver.findElement(By.id("userName")).sendKeys(data.get(0).get("UserName"));
        driver.findElement(By.id("password")).sendKeys(data.get(0).get("Password"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        WebElement ele = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        ewait().until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();

        Thread.sleep(3000);


    }

    @And("User searches book {string} and Select it")
    public void userSearchesBookNameAndSelectIt(String bookName) {
        driver.findElement(By.xpath("//*[@id='searchBox']")).sendKeys(bookName);
        ewait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='see-book-" + bookName + "']")));
        driver.findElement(By.xpath("//*[@id='see-book-" + bookName + "']")).click();

    }

    @And("User adds book to collection")
    public void userAddsBookToCollection() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        ewait().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add To Your Collection')]")));
        driver.findElement(By.xpath("//button[contains(text(),'Add To Your Collection')]")).click();
        Thread.sleep(3000);
        String successMessage = driver.switchTo().alert().getText();
        Assert.assertEquals("Verify alert Text", successMessage, "Book added to your collection.");

        driver.switchTo().alert().accept();
    }

    @And("Navigate to Book Store for search")
    public void navigateToBookStoreForSearch() {

        WebElement scroll = driver.findElement(By.xpath("//*[@id='gotoStore']"));
        scroll.sendKeys(Keys.PAGE_DOWN);
        scroll.click();

    }


    @And("Navigate to profile and verify book {string} added to collection")
    public void navigateToProfileAndVerifyBookAddedToCollection(String bookName) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        driver.findElement(By.xpath("//span[text()='Profile']")).click();
        Thread.sleep(5000);
        String bookXpath = "//a[text()='" + bookName + "']";
        int size = driver.findElements(By.xpath(bookXpath)).size();
        if (size == 1) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Book does not exist in collection");
        }

    }

    @And("Navigate to profile and verify book {string} deleted from collection")
    public void navigateToProfileAndVerifyBookDeletedFromCollection(String bookname) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        driver.findElement(By.xpath("//span[text()='Profile']")).click();
        String delBookPath = "//a[text()='" + bookname + "']/../../../..//*[@id='delete-record-undefined']";
        driver.findElement(By.xpath(delBookPath)).click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        driver.findElement(By.xpath("//button[text()='OK']")).click();

        ewait().until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        String bookXpath = "//a[text()='" + bookname + "']";
        int size = driver.findElements(By.xpath(bookXpath)).size();
        if (size == 0) {
            System.out.println("Book : " + bookname + " Deleted");
        } else {
            System.out.println("Book : " + bookname + " not Deleted");
        }

    }

    @Then("User logout from application")
    public void userLogoutFromApplication() {
        ewait().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Log out']")));
        driver.findElement(By.xpath("//button[text()='Log out']")).click();

        if (driver.findElements(By.xpath("//button[@id='login']")).size() == 1) {
            Assert.assertTrue("Logged out from BookStore successfully", true);
        } else {
            Assert.fail("User is not able to logout from BookStore");
        }
    }

    @And("user verifies all the navigations in the application")
    public void userVerifiesAllTheNavigationsInTheApplication() {
        //this is to validate Profile header
        String headerText = driver.findElement(By.xpath("//*[@class='main-header']")).getText();
        Assert.assertEquals("Navigated to Profile successfully ", headerText, "Profile");

        //validate navigation to book store
        WebElement ele = driver.findElement(By.xpath("//*[@class='btn btn-light ']/*[name()='svg']//..//span[contains(text(),'Book')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
        headerText = driver.findElement(By.xpath("//*[@class='main-header']")).getText();
        Assert.assertEquals("Navigated to Book store successfully ", headerText, "Book Store");

        //validate navigation to Login page
        ele = driver.findElement(By.xpath("//*[@class='btn btn-light ']/*[name()='svg']//..//span[contains(text(),'Login')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
        headerText = driver.findElement(By.xpath("//*[@class='main-header']")).getText();
        Assert.assertEquals("Navigated to login page successfully ", headerText, "Login");


    }
}
