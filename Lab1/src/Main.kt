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
    // headerTests(driver)
    messagesPopupTest(driver)
    // postTests(driver)
    // createPostMenuTests(driver)
    // registrarionTests(driver)

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
    preTestLogin(driver)

    printInfoMsg("\tHEADER tests")
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

fun postTests(driver: ChromeDriver){
    preTestLogin(driver)

    printInfoMsg("\tPOST tests")
    val postTest = PostTest(driver)
    postTest.executeAllTests()
}

fun createPostMenuTests(driver: ChromeDriver) {
    val home = preTestLogin(driver)
    val header = home.header
    val createPost = CreatePostTest(driver)

    printInfoMsg("\tCREATE POST MENU tests")
    header.openCreatePostPopup()
    createPost.textPostOptionTest()

    header.openCreatePostPopup()
    createPost.photoPostOptionTest()

    header.openCreatePostPopup()
    createPost.quotePostOptionTest()

    header.openCreatePostPopup()
    createPost.chatPostOptionTest()

    header.openCreatePostPopup()
    createPost.linkPostOptionTest()

    header.openCreatePostPopup()
    createPost.audioPostOptionTest()

    header.openCreatePostPopup()
    createPost.videoPostOptionTest()
}

// TODO: FIX - DO NOT WORK CORRECTLY
fun messagesPopupTest(driver: ChromeDriver){
    preTestLogin(driver)
    val messagesTest = MessagingPopupTests(driver)

    printInfoMsg("MESSAGES tests")
    messagesTest.cancelMessageTest()
    messagesTest.offeredRecipientMessageTest()
    messagesTest.searchedRecipientMessageTest()
}

private fun preTestLogin(driver: ChromeDriver): HomePage {
    val user = UserCredentialsFactory()
    driver.get("http://tumblr.com/login")
    return LoginPage(driver)
        .fillInEmail(user.getCorrectEmail())
        .fillInPassword(user.getCorrectPassword())
        .login()
}