import PageTests.LoginPageTest
import PageTests.NavigationTest
import PageTests.Registration
import PostTests.PostTest
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver


fun main(args: Array<String>){
    System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe")
    val driver = ChromeDriver()

    try {
        registerUserTest(driver)
        println("Register test was successful")
    }
    catch (e: Exception){
        println("Register test has failed: ${e.message}")
    }

    driver.quit()
}

fun registerUserTest(driver: ChromeDriver) {
    driver.get("http://tumblr.com/register")
    val userFactory = UserFactory()
    var user = userFactory.createUserWithInvalidPassword()

    val registrationPage = RegistrationPage(driver)
    registrationPage
            .registerUserError(user)
            .checkErrorMessage("Пароль не может быть короче 8 символов.")

    user = userFactory.createValidUser()

    registrationPage
            .registerUserSuccess(user)
}

fun testAuthentication(driver: ChromeDriver){
    var authTest = LoginPageTest(driver)
    authTest.testAuthentication()
    // authTest.testLogout()
}

fun testRegistration(driver: ChromeDriver){
    var regTest = Registration(driver)
    regTest.testRegistration()
    regTest.testAccountDelete()
}

fun testPost(driver: ChromeDriver){
    val postTest = PostTest(driver)

    try {
        postTest.beforeTest()
    }
    catch(e: Exception){
        println("Some LOGIN problem. Check network connection or site availability")
        return
    }

    val url = "https://www.tumblr.com/explore/trending"
    driver.get(url)

    try {
        val postContainer = driver.findElement(By.className("posts-holder"))

        println("Performing post tests:")
        // postTest.testPostStructure(postContainer)
        // postTest.testAuthorPostInfo(postContainer)
        // postTest.testPhotoZoom(postContainer)
        postTest.testTags(postContainer)
        postTest.testFooter(postContainer)
    }
    catch (e: NoSuchElementException){
        println("No post is available, cannot perform hole set of tests")
        return
    }

    postTest.afterTest()
}

fun navigationTest(driver: ChromeDriver){
    val navigationTest = NavigationTest(driver)
    navigationTest.headerStructureTest()
    navigationTest.testSearchField()
}
