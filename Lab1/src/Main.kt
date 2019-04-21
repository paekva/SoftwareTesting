import Pages.HomePage
import Pages.LoginPage
import TestCases.*
import Utils.UserCredentialsFactory
import org.openqa.selenium.chrome.ChromeDriver

fun main(){
    System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe")
    val driver = ChromeDriver()

    println("\u001B[36m TESTS HAD STARTED \u001B[0m")
    // loginTests(driver)
    headerTests(driver)
    println("\u001B[36m TESTS HAD FINISHED \u001B[0m")

    /*val regist = RegistTest(driver)
    try{
        regist.registWithEmptyFields()
    }
    catch(e: Exception){
        println(e.message)
    }*/

    /*val dashboardTest = HomePageTest(driver)
    try{
        // dashboardTest.postTest()
    }
    catch(e: Exception){
        println(e.message)
    }*/

    driver.close()
}


fun loginTests(driver: ChromeDriver) {
    val loginTest = LoginTest(driver)

    loginTest.loginWithIncorrectFormatOfEmail()
    loginTest.loginWithUnregisteredEmail()
    loginTest.loginWithEmptyEmail()
    loginTest.loginWithIncorrectPassword()
    loginTest.loginWithEmptyPassword()
    loginTest.loginWithCorrectCredentials()
}


fun headerTests(driver: ChromeDriver) {
    val user = UserCredentialsFactory()
    driver.get("http://tumblr.com/login")
    val login = LoginPage(driver)
        .fillInEmail(user.getCorrectEmail())
        .fillInPassword(user.getCorrectPassword())
        .login()

    val headerTest = HeaderMenuTest(driver)
    headerTest.exploreButtonTest()
    headerTest.inboxButtonTest()
    headerTest.dashboardButtonTest()
    headerTest.messageButtonTest()
    headerTest.activityButtonTest()
    headerTest.accountButtonTest()
    headerTest.logoButtonTest()
    headerTest.searchFieldTest()
    headerTest.createPostButtonTest()
}