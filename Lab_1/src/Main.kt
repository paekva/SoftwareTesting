import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver

fun main(args: Array<String>){
    System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe")
    val driver = ChromeDriver()
    val baseUrl = "https://www.tumblr.com/"
    driver.get(baseUrl)

    val url = "https://www.tumblr.com/explore/trending"
    driver.get(url)

    val post = driver.findElement(By.xpath("//article[starts-with(@className,'is_photo post_tumblelog')]")) // TODO: check xpath
    testPost(post, driver)

    driver.quit()
}

fun testAuthentication(driver: ChromeDriver){
    var authTest = Authentication("paekva@yandex.ru", "rfnz98grf", driver)
    authTest.testLogin()
    authTest.testLogout()
}

fun testPost(post: WebElement, driver: ChromeDriver){
    val postTest = Post(post, driver)
    postTest.test()
}