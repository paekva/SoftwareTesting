package elements

import elements.popups.AccountPopup
import elements.popups.ActivityPopup
import elements.popups.MessagingPopup
import Utils.waitForPresence
import pages.ExplorePage
import pages.HomePage
import pages.InboxPage
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class Header(private val driver: WebDriver){
    @FindBy(id = "search_query")
    private val search: WebElement? = null

    @FindBy(xpath = "//*[@id='home_button']/a")
    private val homeBtn: WebElement? = null

    @FindBy(xpath = "//*[@id='discover_button']/a")
    private val discoverBtn: WebElement? = null

    @FindBy(xpath = "//*[@id='inbox_button']/a")
    private val inboxBtn: WebElement? = null

    @FindBy(xpath = "//*[@id='messaging_button']/button")
    private val messagingBtn: WebElement? = null

    @FindBy(xpath = "//*[@id='activity_button']/a")
    private val activityBtn: WebElement? = null

    @FindBy(xpath = "//*[@id='account_button']/button")
    private val accountBtn: WebElement? = null

    @FindBy(xpath="//button[@title='Создать пост']")
    private val createPostBtn: WebElement? = null

    @FindBy(xpath = "//a[@aria-label='Tumblr']")
    private val logo: WebElement? = null

    init { PageFactory.initElements(driver, this) }

    fun search(){
        search!!.sendKeys(Keys.ENTER)
    }

    fun fillInSearchField(searchText: String) : Header {
        search!!.clear()
        search.sendKeys(searchText)

        waitForPopupToAppear()
        return this
    }

    fun goToHomePageByLogo(): HomePage {
        logo!!.click()

        return HomePage(driver)
    }

    fun goToHomePage(): HomePage {
        homeBtn!!.click()

        return HomePage(driver)
    }

    fun goToExplorePage(): ExplorePage {
        discoverBtn!!.click()

        return ExplorePage(driver)
    }

    fun goToInboxPage(): InboxPage {
        inboxBtn!!.click()

        return InboxPage(driver)
    }

    fun openMessagingPopup() : MessagingPopup {
        messagingBtn!!.click()

        waitForPopupToAppear()

        return MessagingPopup(driver)
    }

    fun openActivityPopup() : ActivityPopup {
        activityBtn!!.click()

        waitForPopupToAppear()

        return ActivityPopup(driver)
    }

    fun openAccountPopup() : AccountPopup {
        accountBtn!!.click()

        waitForPopupToAppear()

        return AccountPopup(driver)
    }

    fun openCreatePostPopup() : CreatePostPopup{
        createPostBtn!!.click()

        return CreatePostPopup(driver)
    }

    private fun waitForPopupToAppear(){
        waitForPresence(driver, 15, "popover_inner")
    }
}
