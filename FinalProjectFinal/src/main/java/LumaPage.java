import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LumaPage {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void typeIn(String xpathElement, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathElement))).sendKeys(text);
    }

    public static void clickOn(String xPathElement) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathElement))).click();
    }

    static Faker faker = new Faker();

    public static String FirstName = faker.name().firstName();
    public static String LastName = faker.name().lastName();
    public static String email = faker.internet().emailAddress();
    public static String password;

    static {
        password = faker.internet().password(8, 25, true, true, true);
    }

    static String ShippingAddress = faker.address().streetAddress();
    static String City = faker.address().cityName();
    static String PostalCode = faker.address().cityPrefix();
    static String Phone = faker.phoneNumber().cellPhone();
    static String createAnAccountLink = "/html/body/div[2]/header/div[1]/div/ul/li[3]/a";
    static String createAnAccountButton = "//*[@id=\"form-validate\"]/div/div[1]/button";

    public static void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 60);
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/");
    }

    public static void scroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
    }
    public static void registerNewValidLumaAccount(){
        clickOn(createAnAccountLink);
        typeIn("//*[@id=\"firstname\"]",FirstName);
        typeIn("//*[@id=\"lastname\"]",LastName);
        typeIn("//*[@id=\"email_address\"]", email);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        typeIn("//*[@id=\"password\"]",password);
        typeIn("//*[@id=\"password-confirmation\"]",password);
        scroll();
        clickOn(createAnAccountButton);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static String pageTitleCheck (){
        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span")));
        return pageTitle.getText();
    }
    public static String successRegistration(){
        WebElement successRegistrationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Thank you for registering with Main Website Store.']")));
        return successRegistrationMsg.getText();
    }

    public static void addToCart() {

        clickOn("//*[@id=\"ui-id-4\"]/span[2]");
        clickOn("//*[@id=\"maincontent\"]/div[4]/div[2]/div[2]/div/ul[2]/li[1]/a");
        clickOn("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[1]/div/a/span/span/img");
        clickOn("//*[@id=\"option-label-size-143-item-172\"]");
        clickOn("//*[@id=\"option-label-color-93-item-56\"]");
        clickOn("//*[@id=\"product-addtocart-button\"]");
        try{
            Thread.sleep(4000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        driver.navigate().back();

        clickOn("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[2]/div/a/span/span/img");
        clickOn("//*[@id=\"option-label-size-143-item-171\"]");
        clickOn("//*[@id=\"option-label-color-93-item-50\"]");
        clickOn("//*[@id=\"product-addtocart-button\"]");
        try{
            Thread.sleep(4000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        clickOn("/html/body/div[2]/header/div[2]/div[1]/a");
        clickOn("//*[@id=\"minicart-content-wrapper\"]/div[2]/div[5]/div/a/span");
        clickOn("//*[@id=\"maincontent\"]/div[3]/div/div[2]/div[1]/ul/li[1]/button");


    }
    public static void shippingDetails(){
        typeIn("//div[@name=\"shippingAddress.street.0\"]//input", ShippingAddress);
        typeIn("//div[@name=\"shippingAddress.city\"]//input", City);
        Select State = new Select(driver.findElement(By.xpath("//select[@name=\"region_id\"]")));
        State.selectByVisibleText("Colorado");
        typeIn("//input[@name=\"postcode\"]", PostalCode);
        Select Country = new Select(driver.findElement(By.xpath("//select[@name=\"country_id\"]")));
        Country.selectByVisibleText("United States");
        typeIn("//input[@name=\"telephone\"]", Phone);
        clickOn("//*[@id=\"checkout-shipping-method-load\"]/table/tbody/tr[1]/td[1]/input");
        scroll();
        clickOn("//*[@id=\"shipping-method-buttons-container\"]/div/button/span");
    }
    public static String checkNumberOfProductsInOrderSummary() {
        WebElement numberOfEntriesInCheckout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"opc-sidebar\"]/div[1]/div/div[1]/strong")));
        return numberOfEntriesInCheckout.getText();
    }

    public static void ReviewAndPayments(){
       clickOn("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button/span");

    }

    public static String getMessageFromThankYouPage() {
        WebElement messageDisplayed =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span")));
        return messageDisplayed.getText();
    }

        public static void closeBrowser() {
        driver.quit();
    }

}