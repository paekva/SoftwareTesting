package elements.popups

import Utils.pressEscKey
import Utils.waitToBeClickable
import elements.UserFullProfile
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class DialogPopup(private val driver: WebDriver) {
    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div[4]/div[1]/textarea")
    private val msgInputField: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div/div[2]/div[1]/a")
    private val author: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div/div[2]/div[2]/a[1]")
    private val moreActionsBtn: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div/div[2]/div[2]/a[2]")
    private val minimizeBtn: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div/div[2]/div[2]/a[3]")
    private val closeBtn: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div[4]/div[2]/div[1]/button[1]")
    private val attachGif: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div[4]/div[2]/div[1]/button[2]")
    private val attachPhoto: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div[4]/div[2]/div[1]/button[3]")
    private val attachSticker: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div[4]/div[2]/div[2]/button")
    private val sendBtn: WebElement? = null

    @FindBy(xpath = "//a[@class='conversation-minimized']")
    private val minimizedConversation: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }

    fun seeAuthor(): UserFullProfile{
        author!!.click()
        waitToBeClickable(driver,20, "text-input")
        return UserFullProfile(driver)
    }

    fun inputMsg(msg: String) : DialogPopup{
        val wait = WebDriverWait(driver,20)
        wait.until(ExpectedConditions.elementToBeClickable(msgInputField))
            .clear()
        msgInputField!!.sendKeys(msg)
        return this
    }

    fun sendMsg(): DialogPopup{
        sendBtn!!.click()
        return this
    }

    fun isSendButtonEnabled(): Boolean{
        return sendBtn!!.isEnabled
    }

    fun attachGif(): DialogPopup{
        attachGif!!.click()
        return this
    }

    fun attachPhoto(): DialogPopup{
        attachPhoto!!.click()
        return this
    }

    fun attachSticker(): DialogPopup{
        attachSticker!!.click()
        return this
    }

    fun closeOptionPopup(): DialogPopup {
        pressEscKey(driver)
        return this
    }
    fun minimizeDialog() : DialogPopup{
        minimizeBtn!!.click()
        return this
    }

    fun maximizeDialog(): DialogPopup{
        minimizedConversation!!.click()
        waitToBeClickable(driver,20, "text-input")
        return this
    }

    fun openSettings(): DialogPopup{
        moreActionsBtn!!.click()
        return this
    }

    fun closePopup(){
        closeBtn!!.click()
    }
}