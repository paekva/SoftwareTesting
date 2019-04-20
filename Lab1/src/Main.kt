import Pages.HomePage
import TestCases.*
import org.openqa.selenium.chrome.ChromeDriver

fun main(){
    System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe")
    val driver = ChromeDriver()

    loginTests(driver)
    // headerTests(driver)
    println()

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

/*
fun headerTests(driver: ChromeDriver) {
    val headerTest = HeaderMenuTest(driver)
    headerTest.dashboardButtonTest()
    headerTest.exploreButtonTest()
    headerTest.inboxButtonTest()
    headerTest.messageButtonTest()
    headerTest.activityButtonTest()
    headerTest.accountButtonTest()
}*/