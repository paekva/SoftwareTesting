import PostTests.PostControls
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class Post(private val driver: ChromeDriver){

    @FindBy(tagName = "img")
    val postContent: WebElement? = null

    @FindBy(className = "tumblelog_info")
    val author: WebElement? = null

    @FindBy(className = "post_content")
    val content: WebElement? = null

    @FindBy(className = "post_tag")
    var tags: List<WebElement>? = null

    @FindBy(className = "post_notes")
    var notes: WebElement? = null

    @FindBy(className = "post_controls")
    var controls: PostControls? = null

    var zoomedContent: WebElement? = null
    var authorPopup: AuthorPopup? = null
    var sharePopup: SharePopup? = null
    var replyPopup: ReplyPopup? = null
    var reblogPopup: ReblogPopup? = null

    init{

    }

    fun openAuthorPopup(){
        val hover = Actions(driver)
        hover.moveToElement(author).build().perform()

        waitForPopupToAppear()

        authorPopup = AuthorPopup(driver)
    }

    fun openSharePopup(){
        controls!!.shareBtn?.click()

        val wait = WebDriverWait(driver, 10)

        sharePopup = SharePopup(driver)
    }

    fun openReplyPopup(){
        controls!!.replyBtn?.click()

        waitForPopupToAppear()

        replyPopup = ReplyPopup(driver)
    }

    fun openReblogPopup(){
        controls!!.reblogBtn?.click()

        waitForPopupToAppear()

        reblogPopup = ReblogPopup(driver)
    }

    fun closePopup(){
        author!!.click()
    }

    fun zoomInContent(){
        postContent!!.click()

        zoomedContent = driver.findElementById("tumblr_lightbox_center_image")
    }

    fun zoomOutContent(){
        driver.findElementById("tumblr_lightbox_center_image").click()
    }

    private fun waitForPopupToAppear(){
        val wait = WebDriverWait(driver, 10)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.className("popover")))
    }
}