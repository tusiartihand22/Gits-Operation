//Created with selenium framework
//Java 8.1 netbeans editor
package selenium;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Gits {
    public WebDriver driver;
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\ASUS\\Downloads\\Compressed\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        Gits automate = new Gits();
        automate.login("lala@gmail.com", "lala121212");
        automate.createPublicGist("My First Gits","First", "Hai, this is my first gits");
        automate.editExistingGits("My First Gits Updated","First Updated", "Hai, this is my first gits and have been update");
        automate.deleteExistingGits();
        automate.viewListGits();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("E:\\KULIAH\\KHS\\TUSIARTI HANDAYANI_HRMLABS PTE LTD\\AirAsia\\AirAsia1Agustus.jpg"));
        driver.close();
   };
   
   public void login(String uname,String passwd){
       boolean loginResult;
       //login step
       driver.navigate().to("https://github.com/login");
       driver.findElement(By.id("login_field")).sendKeys(uname);
       driver.findElement(By.id("password")).sendKeys(passwd);
       driver.findElement(By.name("commit")).click();
       //validate success login
       loginResult = driver.getCurrentUrl().equals("https://github.com/");
       if (loginResult = true)
           System.out.println("Success login");
       else
           System.out.println("Login failed");
   }
   
   public void createPublicGist(String desc, String fileName, String code){
       String gitsFileName;
       //create new public gits
       driver.navigate().to("https://gist.github.com/");
       driver.findElement(By.name("gist[description]")).sendKeys(desc);
       driver.findElement(By.name("gist[contents][][name]")).sendKeys(fileName);
       driver.findElement(By.className("CodeMirror-scroll")).sendKeys(code);
       driver.findElement(By.name("gist[public]")).click();
       //validate public gits successfully created
       gitsFileName = driver.findElement(By.className("user-select-contain gist-blob-name css-truncate-target")).getText();
       gitsFileName.equals(fileName);
   }
   
   public void editExistingGits(String newDesc, String newFileName, String newCode){
       String gitsNewFileName;
       //update existing public gits
       driver.findElement(By.className("btn btn-sm")).click();
       driver.findElement(By.name("gist[description]")).clear();
       driver.findElement(By.name("gist[description]")).sendKeys(newDesc);
       driver.findElement(By.name("gist[contents][][name]")).clear();
       driver.findElement(By.name("gist[contents][][name]")).sendKeys(newFileName);
       driver.findElement(By.className("CodeMirror-scroll")).clear();
       driver.findElement(By.className("CodeMirror-scroll")).sendKeys(newCode);
       driver.findElement(By.className("btn btn-primary")).click();
       //validate public gits successfully updated
       gitsNewFileName = driver.findElement(By.className("user-select-contain gist-blob-name css-truncate-target")).getText();
       gitsNewFileName.equals(newFileName);
   }
   
   public void deleteExistingGits(){
       String successDelete = "Gist deleted successfully.";
       driver.findElement(By.className("btn btn-sm btn-danger")).click();
       driver.findElement(By.className("container-lg px-2")).equals(successDelete);
   }
   
   public void viewListGits(){
       driver.findElement(By.className("Header-link name")).click();
       driver.findElement(By.xpath("/html/body/div[1]/div/div[5]/details/details-menu/a[1]")).click();
       //assert button list gits 
       driver.findElement(By.xpath("/html/body/div[4]/div/main/div/div/div[2]/div[1]/nav/a")).isDisplayed();
       //Check user have gists or not
       
       if (driver.findElement(By.className("gist-snippet")).isDisplayed()){
           driver.findElements(By.className("gist-snippet")).size();
       }
       else{
           String noGits;
           noGits = driver.findElement(By.xpath("/html/body/div[4]/div/main/div/div/div[2]/div[2]/h3")).getText();
           noGits.equals("You don’t have any gists yet.");
       }
   }
}