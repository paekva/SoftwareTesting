package Elements.popups

import Elements.UserFullProfile
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class DialogPopup(private val driver: WebDriver) {
    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div/div[1]/div[4]/div[1]/textarea")
    private val msgInputField: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div/div[2]/div[1]/a")
    private val author: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div/div[2]/div[2]/a[1]")
    private val moreActionsBtn: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div/div[2]/div[2]/a[2]")
    private val minimizeBtn: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div[1]/div[2]/div[2]/a[3]")
    private val closeBtn: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div[4]/div[2]/div[1]/button[1]")
    private val attachGif: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div[4]/div[2]/div[1]/button[2]")
    private val attachPhoto: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div[4]/div[2]/div[1]/button[3]")
    private val attachSticker: WebElement? = null

    @FindBy(xpath = "//*[@class='popover--conversation-popover']/div/div[1]/div[4]/div[2]/div[2]/button")
    private val sendBtn: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }

    fun seeAuthor(): UserFullProfile{
        author!!.click()
        return UserFullProfile(driver)
    }

    fun inputMsg(msg: String) : DialogPopup{
        msgInputField!!.clear()
        msgInputField.sendKeys(msg)
        return this
    }

    fun sendMsg(): DialogPopup{
        sendBtn!!.click()
        return this
    }

    fun isSendButtonEnabled(): Boolean{
        return sendBtn!!.isEnabled
    }

    fun attachGif(){
        attachGif!!.click()
    }

    fun attachPhoto(){
        attachPhoto!!.click()
    }

    fun attachSticker(){
        attachSticker!!.click()
    }
    fun closePopup(){
        closeBtn!!.click()
    }
}