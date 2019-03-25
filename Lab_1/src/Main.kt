import PostTests.PostTest
import org.openqa.selenium.chrome.ChromeDriver

fun main(args: Array<String>){
    System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe")
    val driver = ChromeDriver()
    val baseUrl = "https://www.tumblr.com/"
    driver.get(baseUrl)

    // testAuthentication(driver)
    testPost(driver)
    // testRegistration(driver)

    driver.quit()
}

fun testAuthentication(driver: ChromeDriver){
    var authTest = Authentication(driver)
    authTest.testAuthentication()
    authTest.testLogout()
}

fun testRegistration(driver: ChromeDriver){
    var regTest = Registration(driver)
    regTest.testRegistration()
    regTest.testAccountDelete()
}

fun testPost(driver: ChromeDriver){
    val postTest = PostTest(driver)
    postTest.test()
}