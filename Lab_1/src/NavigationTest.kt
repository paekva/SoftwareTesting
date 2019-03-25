import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.lang.Exception

class NavigationTest(private val driver: ChromeDriver){

    fun headerStructureTest(){
       try{
           val header = HeaderBlock(driver)
           println("Test on header structure was successful")
       }
       catch(e: Exception){
           println("Test on header structure has failed")
       }
    }

    fun testSearchField(){
       try{
           val header = HeaderBlock(driver)
           val searchRequest = "Captain Marvel"
           header.searchField.sendKeys(searchRequest)

           val wait = WebDriverWait(driver, 10)
           wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.id("popover_search")))

           val popupResults = driver.findElementById("search_results_container")
           val resultLink = popupResults.findElement(By.className("result_link"))
           val searchPurpose = resultLink.getAttribute("data-tag-result")

           if(searchPurpose.toLowerCase() != searchRequest.toLowerCase())
               throw Exception("not right search popup content")

           header.sendSearchRequest()

           var searchResultUrl = "https://www.tumblr.com/search/"
           val tagValues = searchRequest.split(' ')

           searchResultUrl += tagValues.reduceIndexed{ idx, acc, it ->
               if (idx == 0) "$acc$it" else  "$acc+$it"
           }

           if(driver.currentUrl!=searchResultUrl)
               throw Exception("not right url")

           println("Successdul test of search field")
       }
       catch(e: Exception){
           println(e.message)
       }
    }
}