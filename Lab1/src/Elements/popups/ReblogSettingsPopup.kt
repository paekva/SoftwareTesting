package Elements.popups

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class ReblogSettingsPopup(private val driver: WebDriver){
    @FindBy(xpath="//*[@id=\"editorType\"]/option[1]")
    private val textFormat: WebElement? = null

    @FindBy(xpath="//*[@id=\"editorType\"]/option[2]")
    private val HTMLFormat: WebElement? = null

    init{
        PageFactory.initElements(driver, this)
    }

    fun chooseFormattedText(){
        textFormat!!.click()
    }

    fun chooseHTMLFormat(){
        HTMLFormat!!.click()
    }
}