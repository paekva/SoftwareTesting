package elements.popups

import Utils.pressEscKey
import Utils.waitToBeClickable
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class MessagingPopup(private val driver: WebDriver) {
    @FindBy(xpath = "//*[@class='messaging-inbox']/div[1]/div[2]/a[text()='Новое сообщение']")
    private val newMsgBtn: WebElement? = null

    @FindBy(xpath = "//*[@class='messaging-inbox']/div[1]/div[2]/a[text()='Отмена']")
    private val cancelNewMsgBtn: WebElement? = null

    @FindBy(xpath = "//*[@class='messaging-inbox']/div[2]/div[1]/label/span[2]/input")
    private val recipientSearchField: WebElement? = null

    @FindBy(xpath = "//*[@data-subview='searchResultView']/div/div[1]/div[1]/a")
    private val searchResultRecipient: WebElement? = null

    @FindBy(xpath = "//*[@data-subview='favoritesView']/div/div[1]/div[1]/a/div/h4")
    private val offeredRecipient: WebElement? = null

    init {
        PageFactory.initElements(driver, this)

        val wait = WebDriverWait(driver, 20)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(newMsgBtn))
    }

    fun writeNewMsg() : MessagingPopup{
        newMsgBtn!!.click()
        return this
    }

    fun cancelNewMsg() : MessagingPopup{
        cancelNewMsgBtn!!.click()
        return this
    }

    fun findRecipient(recipientUsername: String) : MessagingPopup{
        recipientSearchField!!.sendKeys(recipientUsername)

        val wait = WebDriverWait(driver, 30)
        wait.until(ExpectedConditions.elementToBeClickable(searchResultRecipient))

        return this
    }

    fun chooseFoundRecipient() : DialogPopup {
        searchResultRecipient!!.click()

        waitToBeClickable(driver, 20, "close")
        return DialogPopup(driver)
    }

    fun chooseOfferedRecipient() : DialogPopup {
        offeredRecipient!!.click()
        return DialogPopup(driver)
    }

    fun close(){
        pressEscKey(driver)
    }
}
