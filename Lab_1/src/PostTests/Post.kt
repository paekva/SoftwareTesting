package PostTests

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.lang.Exception

class Post(private val post: WebElement, private val driver: ChromeDriver){

    private var author: WebElement
    private var content: WebElement
    var tags: List<WebElement>
    var notes: WebElement
    private var controls: PostControls

    var authorPopup: WebElement?
    var sharePopup: WebElement?
    var replyPopup: WebElement?
    private var reblogPopup: WebElement?

    init{
        author = post.findElement(By.className("tumblelog_info"))
        content = post.findElement(By.className("post_content"))
        tags = post.findElements(By.className("post_tag"))
        notes = post.findElement(By.className("post_notes"))

        val controlsBlock = post.findElement(By.className("post_controls"))
        controls = PostControls(controlsBlock, driver)
        authorPopup = null
        sharePopup = null
        replyPopup = null
        reblogPopup = null
    }

    fun openAuthorPopup(){
        val hover = Actions(driver)
        hover.moveToElement(author).build().perform()

        val wait = WebDriverWait(driver, 10)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.className("tumblelog_popover")))

        authorPopup = driver.findElementByClassName("tumblelog_popover")
    }

    fun closeAuthorPopup(){
        val header = driver.findElement(By.className("l-header"))
        header.click()
    }

    fun zoomInContent(){
        content.findElement(By.tagName("img")).click()
    }

    fun zoomOutContent(){
        driver.findElementById("tumblr_lightbox_center_image").click()
    }

    fun openSharePopup(){
        controls.shareBtn?.click()

        val wait = WebDriverWait(driver, 10)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.className("popover")))

        sharePopup = driver.findElementByClassName("popover")
    }

    fun openReplyPopup(){
        controls.replyBtn?.click()

        val wait = WebDriverWait(driver, 10)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.className("popover")))

        replyPopup = driver.findElementByClassName("popover")
    }

    fun closePopup(){
        author.click()
    }

}
