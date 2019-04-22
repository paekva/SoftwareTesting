import Elements.Header
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
    // headerTests(driver)
    postTests(driver)
    // createPostTests(driver)
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

fun preTestLogin(driver: ChromeDriver): HomePage {
    val user = UserCredentialsFactory()
    driver.get("http://tumblr.com/login")
    return LoginPage(driver)
        .fillInEmail(user.getCorrectEmail())
        .fillInPassword(user.getCorrectPassword())
        .login()
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
    preTestLogin(driver)

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

    val postTest = PostTest(driver)
    postTest.executeAllTests()
}

fun createPostTests(driver: ChromeDriver) {
    val home = preTestLogin(driver)
    val header = home.header
    val createPost = CreatePostTest(driver)

    header!!.openCreatePostPopup()
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