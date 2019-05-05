package elements.popups

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class OptionPopup(private val driver: WebDriver){

    @FindBy(xpath="//button[text()=\"Закрыть\"]")
    private val closeBtn: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }

    fun closePopup(){
        closeBtn!!.click()
    }
}