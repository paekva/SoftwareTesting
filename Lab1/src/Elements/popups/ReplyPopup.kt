package Elements.popups

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class ReplyPopup( private val driver: WebDriver) {
    @FindBy(xpath="//*[@id=\"dashboard_index\"]/div[12]/div/div/div[3]/div/div/div/div/div[1]")
    private var textField: WebElement? = null

    @FindBy(xpath="//*[@id=\"dashboard_index\"]/div[12]/div/div/div[3]/div/button")
    private var sendBtn: WebElement? = null

    init{
        PageFactory.initElements(driver, this)
    }

    fun fillTestField(msg: String){
        textField!!.sendKeys(msg)
    }

    fun sendMsg(){
        sendBtn!!.click()
    }

    fun isSendMsgBtnEnabled(): Boolean{
        return sendBtn!!.isEnabled
    }
}
