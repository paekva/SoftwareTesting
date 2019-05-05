package Pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class SearchPage(private val driver: WebDriver){

    @FindBy(xpath="//*[contains(@class, 'search_results_container']/div/h1")
    private val searchResultHeader: WebElement? = null

    init{
        PageFactory.initElements(driver, this)
    }

    fun getSearchResultHeader(): String{
        return searchResultHeader!!.text
    }
}