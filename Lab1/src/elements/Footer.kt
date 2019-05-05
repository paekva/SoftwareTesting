package elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class Footer(private val driver: WebDriver){

    @FindBy(xpath = "//*[class='footer_links']/li[1]/strong/a")
    private val tumblr: WebElement? = null

    @FindBy(xpath = "//*[class='footer_links']/li[2]/a")
    private val help: WebElement? = null

    @FindBy(xpath = "//*[class='footer_links']/li[3]/a")
    private val about: WebElement? = null

    @FindBy(xpath = "//*[class='footer_links']/li[4]/a")
    private val apps: WebElement? = null

    @FindBy(xpath = "//*[class='footer_links']/li[5]/a")
    private val developers: WebElement? = null

    @FindBy(xpath = "//*[class='footer_links']/li[6]/a")
    private val themes: WebElement? = null

    @FindBy(xpath = "//*[class='footer_links']/li[7]/a")
    private val jobs: WebElement? = null

    @FindBy(xpath = "//*[class='footer_links']/li[3]/a")
    private val new: WebElement? = null

    @FindBy(xpath = "//*[class='footer_links']/li[4]/a")
    private val privacy: WebElement? = null

    init {
        val actions = Actions(driver)
        actions.moveToElement(driver.findElement(By.id("footer_links")))
        actions.perform()
        PageFactory.initElements(driver, this)
    }

    fun openAboutPage(){
        about!!.click()
    }

    fun openTumblrPage(){
        tumblr!!.click()
    }

    fun openHelpPage(){
        help!!.click()
    }

    fun openAppsPage(){
        apps!!.click()
    }

    fun openDevelopersPage(){
        developers!!.click()
    }

    fun openThemesPage(){
        themes!!.click()
    }

    fun openJobsPage(){
        jobs!!.click()
    }

    fun openNewPage(){
        new!!.click()
    }

    fun openPrivacyPage(){
        privacy!!.click()
    }
}