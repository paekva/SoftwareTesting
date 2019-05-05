package Pages

import Elements.UserPost
import Elements.UsersList
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class ExplorePage(val driver: WebDriver) {

    var recommendedPost: UserPost? = null

    var similarBlogsList: UsersList? = null

    @FindBy(xpath="//*[contains(@class,'discover-tumblelogs')]/h1")
    var similarBlogsListHeader: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'discover-controls-wrapper')]/div/div/a[1]")
    private var recommendedBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'discover-controls-wrapper')]/div/div/a[2]")
    private var popularBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'discover-controls-wrapper')]/div/div/a[3]")
    private var ourChoiceBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'discover-controls-wrapper')]/div/div/a[4]")
    private var textBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'discover-controls-wrapper')]/div/div/a[5]")
    private var photoBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'discover-controls-wrapper')]/div/div/a[6]")
    private var gifBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'discover-controls-wrapper')]/div/div/a[7]")
    private var quoteBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'discover-controls-wrapper')]/div/div/a[8]")
    private var chatBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'discover-controls-wrapper')]/div/div/a[9]")
    private var audioBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'discover-controls-wrapper')]/div/div/a[10]")
    private var videoBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'discover-controls-wrapper')]/div/div/a[11]")
    private var questionBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'discover-tags')]/ol/li[1]/a")
    private var discoverTag: WebElement? = null

    private var discoverTagText: String? = ""

    init{
        similarBlogsList = UsersList(driver)
        recommendedPost = UserPost(driver)
        PageFactory.initElements(driver, this)
    }

    fun openRecommendedBtn(){
        recommendedBtn!!.click()
    }

    fun openPopularBtn(){
        popularBtn!!.click()
    }

    fun openChoiceBtn(){
        ourChoiceBtn!!.click()
    }

    fun openTextBtn(){
        textBtn!!.click()
    }

    fun openPhotoBtn(){
        photoBtn!!.click()
    }

    fun openGifBtn(){
        gifBtn!!.click()
    }

    fun openQuoteBtn(){
        quoteBtn!!.click()
    }

    fun openChatBtn(){
        chatBtn!!.click()
    }

    fun openAudioBtn(){
        audioBtn!!.click()
    }

    fun openVideoBtn(){
        videoBtn!!.click()
    }

    fun openQuestionBtn(){
        questionBtn!!.click()
    }

    fun discoverTagSearch() : SearchPage{
        discoverTagText = driver.findElement(By.xpath("//*[contains(@class,'discover-tags')]/ol/li[1]/a/span")).text
        discoverTag!!.click()
        return SearchPage(driver)
    }

    fun discoverTagText() : String{
        return discoverTagText!!.toUpperCase()
    }
}
