import pages.HomePage
import pages.LoginPage
import testCases.*
import Utils.UserCredentialsFactory
import Utils.printInfoMsg
import org.openqa.selenium.chrome.ChromeDriver

fun main(){
    val driver = ChromeDriver()
    printInfoMsg("Test execution has STARTED")

    loginTests(driver)
    registrationTests(driver)

    preTestLogin(driver)
    headerTests(driver)

    printInfoMsg("Test execution has FINISHED")
    driver.close()
}

fun loginTests(driver: ChromeDriver) {
    val loginTest = LoginTest(driver)
    
    printInfoMsg("LOGIN tests")
    loginTest.loginWithIncorrectFormatOfEmail()
    loginTest.loginWithUnregisteredEmail()
    loginTest.loginWithEmptyEmail()
    loginTest.loginWithIncorrectPassword()
    loginTest.loginWithEmptyPassword()
    loginTest.loginWithCorrectCredentials()

    printInfoMsg("LOGIN tests FINISHED")
}

fun registrationTests(driver: ChromeDriver) {
    RegistTest(driver).runAllTests()
}

fun headerTests(driver: ChromeDriver) {
    HeaderMenuTest(driver)
        .runAllTests()
}

private fun preTestLogin(driver: ChromeDriver): HomePage {
    val user = UserCredentialsFactory()
    driver.get("http://tumblr.com/login")
    return LoginPage(driver)
        .fillInEmail(user.getCorrectEmail())
        .fillInPassword(user.getCorrectPassword())
        .login()
}