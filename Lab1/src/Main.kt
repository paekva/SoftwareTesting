import TestCases.*
import org.openqa.selenium.chrome.ChromeDriver

fun main(){
    System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe")
    val driver = ChromeDriver()

    val login = LoginTest(driver)

    try{
        login.loginWithCorrectCredentials()
        /*login.loginWithIncorrectEmailCredentials()
        login.loginWithIncorrectPasswordCredentials()*/
    }
    catch(e: Exception){
        println(e.message)
    }
}
