import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class Post(val driver: ChromeDriver){
    private val auth = Authentication("paekva@yandex.ru", "rfnz98grf", driver)

    private fun beforeTest(){
        val loginUrl = "https://www.tumblr.com/login"
        driver.get(loginUrl)
        auth.login()

    }

    fun test(){
        beforeTest()

        val url = "https://www.tumblr.com/explore/trending"
        driver.get(url)
        val postContainer = driver.findElement(By.className("posts-holder"))
        val post = postContainer.findElement(By.xpath("//ancestor::article"))

        // testPostStructure(post)
        // testAuthorPostInfo(post)
        testPhotoZoom(post)
        testTags(post)
        /*testFooterBtns()
        */
        afterTest()
    }

    private fun testPostStructure(post: WebElement){
        val wait = WebDriverWait(driver, 5)
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post_header")))
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post_content")))
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post_tags")))
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post_footer")))
    }

    private fun testAuthorPostInfo(post: WebElement){
        val author = post.findElement(By.className("tumblelog_info"))
        val hover = Actions(driver)
        hover.moveToElement(author).build().perform()

        val wait = WebDriverWait(driver, 5)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.className("tumblelog_popover")))
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("navigation")))
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("info_popover")))
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("recent_posts")))

        val header = driver.findElement(By.className("l-header"))
        header.click()
    }

    private fun testPhotoZoom(post: WebElement){
        val img = post.findElement(By.xpath("//div[@className='post_media']"))
        /*img.click()
        val wait = WebDriverWait(driver, 5)
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.id("tumblr_lightbox_center_image")))

        val hover = Actions(driver)
        hover.moveToElement(post).build().perform()*/
    }

    private fun testTags(post: WebElement){
        val tagHolder = post.findElement(By.className("post_tags_inner"))
        val tag = tagHolder.findElement(By.tagName("a"))
        val tagValues = tag.text.split(' ')

        var url = "https://www.tumblr.com/search/"
        for(tag in tagValues){
            url += "$tag+"
        }
        tag.click()

        url = url.substring(0,url.length-2)
        if(driver.currentUrl !== url)
            throw Exception("Oops")
    }

    private fun testFooterBtns(){

    }

    private fun afterTest(){
        auth.logout()
    }
}