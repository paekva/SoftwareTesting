package Elements

import Elements.popups.SettingsPopup
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class UserPost(private val driver: WebDriver): Post(driver){
    @FindBy(xpath="//*[contains(@class,'is_recommended')]/div/div[4]/div[2]/div/div[1]")
    override var shareBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'is_recommended')]/div/div[4]/div[2]/div/div[2]")
    override var replyBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'is_recommended')]/div/div[4]/div[2]/div/a")
    override var reblogBtn: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'is_recommended')]/div[2]/div[5]/div[2]/div/div[3]")
    private var likeBtn: WebElement? = null

    init{
        PageFactory.initElements(driver, this)
        println(replyBtn)
    }

    fun likePost() {
        likeBtn!!.click()
    }

    fun isPostLiked(): Boolean {
        return likeBtn!!.getAttribute("liked") != null
    }
}