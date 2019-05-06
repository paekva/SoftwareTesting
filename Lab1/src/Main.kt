import pages.HomePage
import pages.LoginPage
import testCases.*
import Utils.UserCredentialsFactory
import Utils.printInfoMsg
import org.openqa.selenium.chrome.ChromeDriver

fun main(){
    val driver = ChromeDriver()
    printInfoMsg("Test execution has STARTED")

    // loginTests(driver)
    registrationTests(driver)

    // preTestLogin(driver)
    // headerTests(driver)

    // dialogPopupTest(driver)

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
}

fun registrationTests(driver: ChromeDriver) {
    RegistTest(driver).runAllTests()
}

fun headerTests(driver: ChromeDriver) {
    val headerTest = HeaderMenuTest(driver)
    headerTest.runAllTests()
}

// TODO: finish
fun dialogPopupTest(driver: ChromeDriver){
    preTestLogin(driver)
    val dialog = DialogPopupTests(driver)

    printInfoMsg("DIALOG tests")
    dialog.authorProfileTest()
    dialog.minimizeDialogTest()
    dialog.emptyInputTest()
    dialog.correctInputTest()
    dialog.sendGifTest()
    dialog.sendStickerTest()
    dialog.cancelOfSendStickerTest()
}

private fun preTestLogin(driver: ChromeDriver): HomePage {
    val user = UserCredentialsFactory()
    driver.get("http://tumblr.com/login")
    return LoginPage(driver)
        .fillInEmail(user.getCorrectEmail())
        .fillInPassword(user.getCorrectPassword())
        .login()
}