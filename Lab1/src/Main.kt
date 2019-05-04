import Pages.HomePage
import Pages.LoginPage
import TestCases.*
import Utils.UserCredentialsFactory
import Utils.printInfoMsg
import org.openqa.selenium.chrome.ChromeDriver

fun main(){
    val driver = ChromeDriver()
    printInfoMsg("Test execution has STARTED")

    // loginTests(driver)
    // registrarionTests(driver)

    preTestLogin(driver)
    headerTests(driver)
    footerTests(driver)

    // dialogPopupTest(driver)

    printInfoMsg("Test execution has FINISHED")
    driver.close()
}

fun loginTests(driver: ChromeDriver) {
    val loginTest = LoginTest(driver)
    
    printInfoMsg("\tLOGIN tests")
    loginTest.loginWithIncorrectFormatOfEmail()
    loginTest.loginWithUnregisteredEmail()
    loginTest.loginWithEmptyEmail()
    loginTest.loginWithIncorrectPassword()
    loginTest.loginWithEmptyPassword()
    loginTest.loginWithCorrectCredentials()
}

fun registrarionTests(driver: ChromeDriver) {
    printInfoMsg("\tREGISTRATION tests")
}

fun headerTests(driver: ChromeDriver) {
    val headerTest = HeaderMenuTest(driver)
    headerTest.runAllTests()
}

fun footerTests(driver: ChromeDriver){
    val footerTest = FooterMenuTest(driver)
    footerTest.runAllTests()
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