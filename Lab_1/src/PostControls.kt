import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class PostControls(private val controlsBlock: WebElement, private val driver: ChromeDriver){

    fun test(){
        try{
            val shareBtn = controlsBlock.findElement(By.className("share"))
            shareBtnTest(shareBtn)

            val replyBtn = controlsBlock.findElement(By.className("reply"))
            val reblogBtn = controlsBlock.findElement(By.className("reblog"))
            val likeBtn = controlsBlock.findElement(By.className("like"))

            println("Footer controls test was successful")
        }
        catch(e: Exception){
            println("Footer controls test has failed")
        }
    }

    private fun shareBtnTest(shareBtn: WebElement){
        try{
            shareBtn.click()
            val wait = WebDriverWait(driver, 5)
            wait.until<WebElement>(ExpectedConditions.presenceOfElementLocated(By.className("messaging-share-post")))

            val searchField = controlsBlock.findElement(By.className("messaging-share-post-search"))
            val mainPart = controlsBlock.findElement(By.className("messaging-share-post-main"))
            val shareOptionsWrapper = controlsBlock.findElement(By.className("messaging-share-post-external-networks-wrapper"))

            val options = shareOptionsWrapper.findElements(By.tagName("a"))
            for(option in options){
                wait.until(ExpectedConditions.elementToBeClickable(option))
            }

            // INSERT CHECK
            options[1].click()
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")))
            val holder = driver.findElementByClassName("messaging-share-post-external-network-subview-wrapper")
            val backBtn = holder.findElement(By.className("back-button"))
            backBtn.click()

            // EMAIL SHARE


            println("Footer controls: SHARE BUTTON test was successful")
        }
        catch (e: Exception){
            println("Footer controls: SHARE BUTTON test has failed")
        }
    }
}