package elements.popups

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class EmailPopup(private val driver: WebDriver){
    @FindBy(xpath="//*[contains(@class,'messaging-share-post-external-network-subview-wrapper')]/div[2]/form/div/input")
    private val emailInput: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'messaging-share-post-external-network-subview-wrapper')]/div[2]/form/div/textarea")
    private val msgInput: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'messaging-share-post-external-network-subview-wrapper')]/div[2]/form/footer/button")
    private val submitButton: WebElement? = null

    @FindBy(xpath="//a[contains(@class,'back-button')]")
    private val backButton: WebElement? = null

    init{
        PageFactory.initElements(driver, this)
    }

    fun fillInEmail(email: String){
        emailInput!!.sendKeys(email)
    }

    fun fillInMsg(msg: String){
        msgInput!!.sendKeys(msg)
    }

    fun isSubmitEnabled(): Boolean{
        return submitButton!!.isEnabled
    }

    fun submit(){
        submitButton!!.click()
    }

    fun closeEmailPopup(){
        backButton!!.click()
    }
}