import org.openqa.selenium.chrome.ChromeDriver

fun main(args: Array<String>){
    System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe")
    val driver = ChromeDriver()
    val baseUrl = "https://www.tumblr.com/"
    driver.get(baseUrl)

    var authTest = Authentication("paekva@yandex.ru", "rfnz98grf", driver)
    authTest.testLogin()
    authTest.testLogout()

    driver.quit()
}
