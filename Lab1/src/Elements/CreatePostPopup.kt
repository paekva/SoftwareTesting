package Elements

import Elements.popups.OptionPopup
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class CreatePostPopup(private val driver: WebDriver){

    @FindBy(xpath = "//*[@data-post-type='text']")
    private val textOption: WebElement? = null

    @FindBy(xpath = "//*[@data-post-type='photo']/div")
    private val photoOption: WebElement? = null

    @FindBy(xpath = "//*[@data-post-type='quote']/div")
    private val quoteOption: WebElement? = null

    @FindBy(xpath = "//*[@data-post-type='link']/div")
    private val linkOption: WebElement? = null

    @FindBy(xpath = "//*[@data-post-type='chat']")
    private val chatOption: WebElement? = null

    @FindBy(xpath = "//*[@data-post-type='audio']/div")
    private val audioOption: WebElement? = null

    @FindBy(xpath = "//*[@data-post-type='video']/div")
    private val videoOption: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }

    fun createTextPost(): OptionPopup {
        textOption!!.click()
        waitForPopupToAppear()

        return OptionPopup(driver)
    }

    fun createPhotoPost(): OptionPopup {
        photoOption!!.click()
        waitForPopupToAppear()

        return OptionPopup(driver)
    }

    fun createQuotePost(): OptionPopup {
        quoteOption!!.click()
        waitForPopupToAppear()

        return OptionPopup(driver)
    }

    fun createLinkPost(): OptionPopup {
        linkOption!!.click()
        waitForPopupToAppear()

        return OptionPopup(driver)
    }

    fun createChatPost(): OptionPopup {
        chatOption!!.click()
        waitForPopupToAppear()

        return OptionPopup(driver)
    }

    fun createAudioPost(): OptionPopup {
        audioOption!!.click()
        waitForPopupToAppear()

        return OptionPopup(driver)
    }

    fun createVideoPost(): OptionPopup {
        videoOption!!.click()
        waitForPopupToAppear()

        return OptionPopup(driver)
    }

    private fun waitForPopupToAppear(){
        val wait = WebDriverWait(driver, 30)
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("post-container")))
    }
}