import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class Post(val post: WebElement, val driver: ChromeDriver){
    private val auth = Authentication("paekva@yandex.ru", "rfnz98grf", driver)

    private fun beforeTest(){
        val loginUrl = "https://www.tumblr.com/login"
        driver.get(loginUrl)

        auth.login()
    }

    fun test(){
        beforeTest()

        testPostStructure()
        testAuthorPostInfo()
        testPhotoZoom()
        testTags()
        testFooterBtns()

        afterTest()
    }

    private fun testPostStructure(){
        val wait = WebDriverWait(driver, 5)
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post_header")))
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post_content")))
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post_tags")))
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post_footer")))
    }

    private fun testAuthorPostInfo(){
        val author = post.findElement(By.className("tumblelog_info"))
        val hover = Actions(driver)
        hover.moveToElement(author).build().perform()


    }

    private fun testPhotoZoom(){

    }

    private fun testTags(){

    }

    private fun testFooterBtns(){

    }

    private fun afterTest(){
        auth.logout()
    }
}