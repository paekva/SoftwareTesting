package elements.popups

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class ReblogPopup(private val driver: WebDriver){

    @FindBy(xpath = "//*[contains(@class, 'control left')]/div/button")
    private var leftControl: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'popover--tumblelog-select-dropdown')]")
    private var popMenu: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'post-settings')]")
    private var settingsBtn: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'post-form--bottom')]/div/div/div[1]/button")
    private var closeBtn: WebElement? = null

    @FindBy(xpath = "//*[@aria-label='Подпись']")
    private var textFormatField: WebElement? = null

    @FindBy(xpath = "//*[@aria-label='HTML-содержимое']")
    private var htmlFormatField: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'create_post_button')]")
    private var reblogBtn: WebElement? = null

    @FindBy(xpath = "//*[contains(@class, 'dropdown-area')]")
    private var reblogDropdownBtn: WebElement? = null


    init{
        PageFactory.initElements(driver, this)
    }

    fun fillInMsgField(msg: String) {
        textFormatField!!.sendKeys(msg)
    }

    fun getTextFormatField(): WebElement?{
        return textFormatField
    }

    fun getHtmlFormatField(): WebElement?{
        return htmlFormatField
    }

    fun closePopup(){
        closeBtn!!.click()

        val wait = WebDriverWait(driver, 20)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dialog_0\"]/div/div/div[2]/button")))
            .click()
    }

    fun reblog(){
        reblogBtn!!.click()
    }

    fun openSettings(): ReblogSettingsPopup{
        settingsBtn!!.click()

        return ReblogSettingsPopup(driver)
    }

    fun closeSettings() {
        settingsBtn!!.click()
    }

    fun openDropdown(): ReblogDropdownPopup{
        reblogDropdownBtn!!.click()

        return ReblogDropdownPopup(driver)
    }

    fun closeDropdown(){
        reblogDropdownBtn!!.click()
    }

    fun openAuthorLabel(): WebElement? {
        leftControl!!.click()
        driver.findElement(By.xpath("//*[contains(@class,'popover--tumblelog-select-dropdown')]")).click()

        return popMenu
    }

    fun closeAuthorLabel(){
        leftControl!!.click()
    }
}
