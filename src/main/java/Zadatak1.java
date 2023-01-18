import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

/*
Automatizovati navodjenje na sajtu https://automationexercise.com.
Otici na pocetnu stranu, kliknuti na "Signup / Login", unesite u polja za "New User Signup!" pomocu faker-a - ime i email.
Kliknuti dugme Signup.
Pored neophodnih polja na Signup ekranu uneti i date of birth, cekirati Title i "Receive special offers from our partners!".
Country staviti na "Canada".
Docekace vas ekran Account created, kliknuti "Continue". Vratice vas na pocetnu stranicu.
Za kraj kliknuti Delete Account, opet kliknuti Continue.
 */
public class Zadatak1 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ivan\\Documents\\IT Bootcamp\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  nisam morala da koristim wait

        driver.get("https://automationexercise.com");

        WebElement signupLogin = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a"));
        signupLogin.click();

        Faker faker = new Faker();

        WebElement name = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[2]"));
        name.sendKeys(faker.name().username());

        WebElement email = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]"));
        email.sendKeys(faker.internet().emailAddress());

        WebElement signupButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button"));
        signupButton.click();

        WebElement mrButton = driver.findElement(By.id("id_gender1"));
        mrButton.click();

        WebElement mrLabel = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/form/div[1]/div[1]/label"));
        mrLabel.click();

        WebElement mrsButton = driver.findElement(By.id("id_gender2"));
        mrsButton.click();

        //WebElement mrsLabel = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/form/div[1]/div[2]/label"));
        //mrsLabel.click();         provera drugog label

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys(faker.internet().password());

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0,400);", "");

        WebElement selectDay = driver.findElement(By.id("days"));
        Select dayOfBirth = new Select(selectDay);
        dayOfBirth.selectByVisibleText("9");

        WebElement selectMonth = driver.findElement(By.id("months"));
        Select monthOfBirth = new Select(selectMonth);
        monthOfBirth.selectByVisibleText("August");

        WebElement selectYear = driver.findElement(By.id("years"));
        Select yearOfBirth = new Select(selectYear);
        yearOfBirth.selectByVisibleText("1988");

//ovo nije radilo dok nisam uvela scroll, radi i bez implicit wait što sam prvo pokušala
        //WebElement newsletterLabel = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/form/div[6]/label"));
        //newsletterLabel.click();
        WebElement newsletterBox = driver.findElement(By.id("newsletter"));
        newsletterBox.click();

        //WebElement offersLabel = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/form/div[7]/label"));
        //offersLabel.click();
        WebElement offersBox = driver.findElement(By.id("optin"));
        offersBox.click();

        WebElement firstName = driver.findElement(By.id("first_name"));
        firstName.sendKeys(faker.name().firstName());

        WebElement lastName = driver.findElement(By.id("last_name"));
        lastName.sendKeys(faker.name().lastName());
//opet mora scroll
        javascriptExecutor.executeScript("window.scrollBy(0,600);", "");

        WebElement address = driver.findElement(By.id("address1"));
        address.sendKeys(faker.address().streetAddress());

        WebElement selectCountry = driver.findElement(By.id("country"));
        Select country = new Select(selectCountry);
        country.selectByVisibleText("Canada");

        WebElement state = driver.findElement(By.id("state"));
        state.sendKeys("Canada");

        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys("Ottawa");

        WebElement zipcode = driver.findElement(By.id("zipcode"));
        zipcode.sendKeys(faker.address().zipCode());

        WebElement mobileNumber = driver.findElement(By.id("mobile_number"));
        mobileNumber.sendKeys(faker.phoneNumber().cellPhone());

        WebElement createAccount = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/form/button"));
        createAccount.submit();

        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a"));
        continueButton.click();

        //OVO JE TAMARINO REŠENJE
        //bez ove linije koda delete ne radi zbog reklame koja se uvek javljala prilikom pokretanja
        driver.navigate().to("https://automationexercise.com/%22");

        WebElement deleteAccount = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a"));
        deleteAccount.click();

        WebElement continueBtn = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a"));
        continueBtn.click();








    }
}
