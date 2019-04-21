package Elements

import Pages.ExplorePage
import Pages.HomePage
import Pages.InboxPage
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class Header(private val driver: WebDriver){
    @FindBy(id = "search_query")
    private val search: WebElement? = null

    @FindBy(xpath = "//*[@id=\"home_button\"]/a")
    private val homeBtn: WebElement? = null

    @FindBy(xpath = "//*[@id=\"discover_button\"]/a")
    private val discoverBtn: WebElement? = null

    @FindBy(xpath = "//*[@id=\"inbox_button\"]/a")
    private val inboxBtn: WebElement? = null

    @FindBy(xpath = "//*[@id=\"messaging_button\"]/button")
    private val messagingBtn: WebElement? = null

    @FindBy(xpath = "//*[@id=\"activity_button\"]/a")
    private val activityBtn: WebElement? = null

    @FindBy(xpath = "//*[@id=\"account_button\"]/button")
    private val accountBtn: WebElement? = null

    @FindBy(xpath="//button[@title='Создать пост']")
    private val createPostBtn: WebElement? = null

    @FindBy(xpath = "//a[@aria-label='Tumblr']")
    private val logo: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }

    fun search(){
        search!!.sendKeys(Keys.ENTER)
    }

    fun fillInSearchField(searchText: String): SearchPopup? {
        search!!.clear()
        search.sendKeys(searchText)

        waitForPopupToAppear()

        return SearchPopup(driver)
    }

    fun goToDashboardByLogo(): HomePage {
        logo!!.click()

        return HomePage(driver)
    }

    fun goToDashboard(): HomePage {
        homeBtn!!.click()

        return HomePage(driver)
    }

    fun goToExplore(): ExplorePage {
        discoverBtn!!.click()

        return ExplorePage(driver)
    }

    fun goToInbox(): InboxPage {
        inboxBtn!!.click()

        return InboxPage(driver)
    }

    fun openMessagingPopup() : MessagingPopup{
        messagingBtn!!.click()

        waitForPopupToAppear()

        return MessagingPopup(driver)
    }

    fun openActivityPopup() : ActivityPopup{
        activityBtn!!.click()

        waitForPopupToAppear()

        return ActivityPopup(driver)
    }

    fun openAccountPopup() : AccountPopup{
        accountBtn!!.click()

        waitForPopupToAppear()

        return AccountPopup(driver)
    }

    fun openCreatePostPopup() : CreatePostPopup{
        createPostBtn!!.click()

        return CreatePostPopup(driver)
    }

    fun closeMessagingPopup(): Header{
        messagingBtn!!.click()
        return this
    }

    fun closeActivityPopup(): Header{
        activityBtn!!.click()
        return this
    }

    fun closeAccountPopup(): Header{
        accountBtn!!.click()
        return this
    }

    fun closeCreatePostPopup(): Header{
        driver.get("https://www.tumblr.com/dashboard")

        return this
    }

    private fun waitForPopupToAppear(){
        val wait = WebDriverWait(driver, 10)
        wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("popover_inner")))
    }

}
