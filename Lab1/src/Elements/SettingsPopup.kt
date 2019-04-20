package Elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class SettingsPopup(private val popupElement: WebElement, private val driver: WebDriver){

    var changeBtn: WebElement? = null
    var deleteBtn: WebElement? = null

    var changePopup: ReblogPopup? = null

    init{
        changeBtn = popupElement.findElement(By.xpath("//ul/li[1]/a"))
        deleteBtn = popupElement.findElement(By.xpath("//ul/li[2]/a"))
        println("found all btns")
    }

    fun openChangePopup(){
        changeBtn!!.click()

        val popup = waitForPopupToAppear("popover")

        println("found one change popup")
        changePopup = ReblogPopup(popup, driver)

    }

    protected fun waitForPopupToAppear(popover: String): WebElement{
        val wait = WebDriverWait(driver, 30)
        val popup = wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className(popover)))
        return popup
    }
}
