package elements

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class UserPost(private val driver: WebDriver): Post(driver){
    @FindBy(xpath="//*[contains(@class,'not_mine')]/div/div[2]/div/div[1]")
    override var shareBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'not_mine')]/div/div[2]/div/div[2]")
    override var replyBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'not_mine')]/div/div[2]/div/a")
    override var reblogBtn: WebElement? = null

    @FindBy(xpath = "//*[contains(@class,'not_mine')]/header/div")
    override var author: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'not_mine')]/div/div[2]/div/div[3]")
    private var likeBtn: WebElement? = null

    init{
        PageFactory.initElements(driver, this)
    }

    fun likePost() {
        likeBtn!!.click()
    }

    fun isPostLiked(): Boolean {
        println(likeBtn!!.getAttribute("liked"))
        return likeBtn!!.getAttribute("liked") != null
    }
}