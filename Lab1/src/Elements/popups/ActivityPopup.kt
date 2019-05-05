package elements.popups

import elements.UserFullProfile
import Utils.pressEscKey
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class ActivityPopup(private val driver: WebDriver) {

    @FindBy(xpath="//*[contains(@class,'popover--activity-popover')]/div/div/div[2]/div/a")
    private val seeAllBtn: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'popover--activity-popover')]/div/div/div[2]/div/div/div/ul/li[2]/a")
    private val activityResult: WebElement? = null

    init {
        PageFactory.initElements(driver, this)

        val wait = WebDriverWait(driver, 10)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(seeAllBtn))
    }

    fun seeAllResults() {
        seeAllBtn!!.click()
    }

    fun seeOneResult() : UserFullProfile{
        activityResult!!.click()
        return UserFullProfile(driver)
    }

    fun close(){
        pressEscKey(driver)
    }
}
