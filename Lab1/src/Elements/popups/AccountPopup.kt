package elements.popups

import Utils.pressEscKey
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class AccountPopup(private val driver: WebDriver) {

    @FindBy(xpath = "//*[contains(@class, 'popover--account-popover')]/div/div/ul/li[1]/ul/li[1]/a")
    private var likes: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'popover--account-popover')]/div/div/ul/li[1]/ul/li[2]/a")
    private var follow: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'popover--account-popover')]/div/div/ul/li[1]/ul/li[3]/a")
    private var settings: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'popover--account-popover')]/div/div/ul/li[1]/ul/li[4]/a")
    private var reference: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'popover--account-popover')]/div/div/ul/li[2]/ul/li/ul/li/div[1]/a")
    private var user: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'popover--account-popover')]/div/div/ul/li[2]/ul/li/ul/li/div[2]/ul/li[1]/a")
    private var postsList: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'popover--account-popover')]/div/div/ul/li[2]/ul/li/ul/li/div[2]/ul/li[2]/a")
    private var readersList: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'popover--account-popover')]/div/div/ul/li[2]/ul/li/ul/li/div[2]/ul/li[3]/a")
    private var activity: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'popover--account-popover')]/div/div/ul/li[2]/ul/li/ul/li/div[2]/ul/li[4]/a")
    private var markedPosts: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'popover--account-popover')]/div/div/ul/li[2]/ul/li/ul/li/div[2]/ul/li[5]/a")
    private var appearanceSettings: WebElement? = null

    init{
        PageFactory.initElements(driver, this)

        val wait = WebDriverWait(driver, 20)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(likes))
    }

    fun openLikesList() {
        likes!!.click()
    }

    fun openFollowList() {
        follow!!.click()
    }

    fun openSettings() {
        settings!!.click()
    }

    fun openReference() {
        reference!!.click()
    }

    fun openUsersProfile() {
        user!!.click()
    }

    fun openPostsList() {
        postsList!!.click()
    }

    fun openReadersList() {
        readersList!!.click()
    }

    fun openActivity() {
        activity!!.click()
    }

    fun openMarkedPosts() {
        markedPosts!!.click()
    }

    fun openAppearanceSettings() {
        appearanceSettings!!.click()
    }

    fun close(){
        pressEscKey(driver)
    }
}
