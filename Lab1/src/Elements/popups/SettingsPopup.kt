package Elements.popups

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class SettingsPopup(private val driver: WebDriver){

    @FindBy(xpath = "//*[contains(@class,'is_reblog')]/div/div[4]/div[2]/div/div[3]/div/ul/li[1]/a")
    private var changeBtn: WebElement? = null

    @FindBy(xpath = "//*[contains(@class,'is_reblog')]/div/div[4]/div[2]/div/div[3]/div/ul/li[2]/div")
    private var deleteBtn: WebElement? = null

    init{
        PageFactory.initElements(driver, this)
    }

    fun openChangePopup(): ReblogPopup{
        changeBtn!!.click()

        waitForPopupToAppear("popover")

        return ReblogPopup(driver)
    }

    fun delete() {
        deleteBtn!!.click()

        val wait = WebDriverWait(driver, 30)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'buttons')]/div[1]/button"))).click()
    }

    private fun waitForPopupToAppear(popover: String): WebElement{
        val wait = WebDriverWait(driver, 30)
        val popup = wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className(popover)))
        return popup
    }
}
