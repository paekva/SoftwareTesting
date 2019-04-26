package Elements

import Elements.popups.SettingsPopup
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class UserPost(private val driver: WebDriver): Post(driver){
    @FindBy(xpath = "//*[contains(@class, 'not_mine')]/div[2]/div[5]/div[2]/div/div[3]")
    private var likeBtn: WebElement? = null

    init{
        PageFactory.initElements(driver, this)
    }

    fun likePost() {
        likeBtn!!.click()
    }

    fun isPostLiked(): Boolean {
        return likeBtn!!.getAttribute("liked") != null
    }
}