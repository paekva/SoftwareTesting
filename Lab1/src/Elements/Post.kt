package Elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class Post(private val driver: WebDriver, private val postElement: WebElement){

    var postContent: WebElement? = null
    var author: WebElement? = null
    var content: WebElement? = null
    var tags: List<WebElement>? = null
    var notes: WebElement? = null

    //@FindBy(className = "post_controls")
    // var controls: PostControls? = null

    var zoomedContent: WebElement? = null
    var authorPopup: AuthorPopup? = null
    var sharePopup: SharePopup? = null
    var replyPopup: ReplyPopup? = null
    var reblogPopup: ReblogPopup? = null

    init{
        postContent = postElement.findElement(By.tagName("img"))
    }

    fun openAuthorPopup(){
        val hover = Actions(driver)
        hover.moveToElement(author).build().perform()

        waitForPopupToAppear()

        authorPopup = AuthorPopup()
    }

    fun openSharePopup(){
        // controls!!.shareBtn?.click()

        val wait = WebDriverWait(driver, 10)

        sharePopup = SharePopup()
    }

    fun openReplyPopup(){
        // controls!!.replyBtn?.click()

        waitForPopupToAppear()

        replyPopup = ReplyPopup()
    }

    fun openReblogPopup(){
        // controls!!.reblogBtn?.click()

        waitForPopupToAppear()

        reblogPopup = ReblogPopup()
    }

    fun closePopup(){
        author!!.click()
    }

    /*fun zoomInContent(){
        postContent!!.click()

        zoomedContent = driver.findElementById("tumblr_lightbox_center_image")
    }

    fun zoomOutContent(){
        driver.findElementById("tumblr_lightbox_center_image").click()
    }*/

    private fun waitForPopupToAppear(){
        val wait = WebDriverWait(driver, 10)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.className("popover")))
    }
}
