package Elements.popups

import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class SharePopup(private val driver: WebDriver) {

    @FindBy(xpath="//div/div/div[1]/div/div/input")
    var searchField: WebElement? = null

    @FindBy(xpath="//*[contains(@class,'messaging-share-post-results')]")
    var sharedList: WebElement? = null

    @FindBy(xpath="//a[contains(@class,'network--permalink')]")
    private var permLinkBtn: WebElement? = null

    @FindBy(xpath="//a[contains(@class,'network--copy-permalink')]")
    private var copyLinkBtn: WebElement? = null

    @FindBy(xpath="//a[contains(@class,'network--embed')]")
    private var embedBtn: WebElement? = null

    @FindBy(xpath="//a[contains(@class,'network--email')]")
    private var emailBtn: WebElement? = null

    @FindBy(xpath="//a[contains(@class,'network--flag')]")
    private var abuseBtn: WebElement? = null

    private var emailPopup: EmailPopup? = null

    init{
        PageFactory.initElements(driver, this)
    }

    fun usePermLink(){
        permLinkBtn!!.click()
    }

    fun returnFromPermLink(){
        driver.switchTo().window(driver.windowHandles.last())

        val action = Actions(driver)
        action.sendKeys(Keys.ESCAPE).perform()
    }

    fun useCopyPermLink(){
        copyLinkBtn!!.click()
    }

    fun returnFromCopyPermLink(){
        driver.findElement(By.className("ui_button")).click()
    }

    fun useEmbed(){
        embedBtn!!.click()
    }

    fun returnFromEmbed(){
        driver.findElement(By.className("back-button")).click()
    }

    fun useEmail(): EmailPopup?{
        emailBtn!!.click()
        emailPopup = EmailPopup(driver)

        return emailPopup
    }

    fun returnFromEmail(){
        emailPopup!!.closeEmailPopup()
    }

    fun useAbuse(){
        abuseBtn!!.click()
    }

    fun returnFromAbuse(){
        val action = Actions(driver)
        action.sendKeys(Keys.ESCAPE).perform()
    }

    fun horizontalScroll(){
        val jse = driver as JavascriptExecutor
        val about = driver.findElement(By.xpath("//*[@data-subview='networkView[]']"))
        jse.executeScript("arguments[0].scrollIntoView();", about)
    }

}
