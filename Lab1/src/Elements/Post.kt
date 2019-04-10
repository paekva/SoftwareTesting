package Elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

open class Post(private val driver: WebDriver, private val postElement: WebElement){

    var postContent: WebElement? = null
    var shareBtn: WebElement? = null
    var replyBtn: WebElement? = null
    var reblogBtn: WebElement? = null

    var zoomedContent: WebElement? = null
    var sharePopup: SharePopup? = null
    var replyPopup: ReplyPopup? = null
    var reblogPopup: ReblogPopup? = null

    /*var author: WebElement? = null
    var content: WebElement? = null
    var tags: List<WebElement>? = null
    var notes: WebElement? = null
     var controls: PostControls? = null

    var zoomedContent: WebElement? = null
    var authorPopup: AuthorPopup? = null*/

    init{
        postContent = postElement.findElement(By.tagName("img"))
        shareBtn = postElement.findElement(By.xpath("//*/div[2]/div[3]/div[2]/div/div[1]"))
        replyBtn = postElement.findElement(By.xpath("//*/div[2]/div[3]/div[2]/div/div[2]"))
        reblogBtn = postElement.findElement(By.xpath("//*/div[2]/div[3]/div[2]/div/a"))
    }

    fun openSharePopup(){
        shareBtn!!.click()

        val popup = waitForPopupToAppear("popover--messaging-share-post")

        sharePopup = SharePopup(popup, driver)

    }

    fun openReplyPopup(){
        replyBtn!!.click()

        val popup = waitForPopupToAppear("post-activity-popover")

        replyPopup = ReplyPopup(popup, driver)
    }

    fun openReblogPopup(){
        reblogBtn!!.click()

        val popup = waitForPopupToAppear("post-form")

        reblogPopup = ReblogPopup(popup, driver)
    }

    protected fun waitForPopupToAppear(popover: String): WebElement{
        val wait = WebDriverWait(driver, 30)
        val popup = wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className(popover)))
        return popup
    }
}

/*fun openAuthorPopup(){
       val hover = Actions(driver)
       hover.moveToElement(author).build().perform()

       waitForPopupToAppear()

       authorPopup = AuthorPopup()
   }*/
/*fun zoomInContent(){
    postContent!!.click()

    zoomedContent = driver.findElementById("tumblr_lightbox_center_image")
}

fun zoomOutContent(){
    driver.findElementById("tumblr_lightbox_center_image").click()
}*/