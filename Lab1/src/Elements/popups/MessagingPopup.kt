package Elements.popups

import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class MessagingPopup(private val driver: WebDriver) {
    @FindBy(xpath = "//*[@class='messaging-inbox']/div[1]/div[2]/a[1][text()='Новое сообщение']")
    private val newMsgBtn: WebElement? = null

    @FindBy(xpath = "//*[@class='messaging-inbox']/div[1]/div[2]/a[1][text()='Отмена']")
    private val cancelNewMsgBtn: WebElement? = null

    @FindBy(xpath = "//*[@class='messaging-inbox']/div[2]/div[1]/label/span[2]/input")
    private val recipientSearchField: WebElement? = null

    @FindBy(xpath = "//*[@class='inbox-recipients' and @data-subview='searchResultView']/div[1]/div[1]/div[1]/a[1]")
    private val searchResultRecipient: WebElement? = null

    @FindBy(xpath = "//*[@class='inbox-recipients' and @data-subview='favoritesView']/div[1]/div[1]/div[@class='inbox-recipient']/a[1]")
    private val offeredRecipient: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
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
        println("chooseFoundRecipient")
        println(searchResultRecipient)
        searchResultRecipient!!.click()
        return DialogPopup(driver)
    }

    fun chooseOfferedRecipient() : DialogPopup {
        println("chooseOfferedRecipient")
        println(offeredRecipient)
        offeredRecipient!!.click()
        println(offeredRecipient)
        return DialogPopup(driver)
    }

    fun closePopup(){
        val action = Actions(driver)
        action.sendKeys(Keys.ESCAPE).perform()
    }
}
