import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
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

    @FindBy(xpath = "//*[@id='dashboard_index']/div[2]/div/button")
    private val dashbord: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
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

    fun closePopup(){}

    private fun waitForPopupToAppear(){
        val wait = WebDriverWait(driver, 10)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.className("popover")))
    }

}