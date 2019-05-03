package Elements.popups

import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class ActivityPopup(private val driver: WebDriver) {

    @FindBy(xpath="//*[contains(@class,'popover--activity-popover')]/div/div/div[2]/div/a")
    private val seeAllBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'popover--activity-popover')]/div/div/div[2]/div/div/ul/li[2]")
    private val activityResult: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }

    fun seeAllResults() {
        seeAllBtn!!.click()
    }

    fun seeOneResult() : AccountPopup{
        activityResult!!.click()
        return AccountPopup(driver)
    }

    fun closePopup(){
        val action = Actions(driver)
        action.sendKeys(Keys.ESCAPE).perform()
    }
}
