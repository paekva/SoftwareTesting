package PostTests

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class PostControls(private val controlsBlock: WebElement, private val driver: ChromeDriver){

    var shareBtn: WebElement?
    var replyBtn: WebElement?
    var reblogBtn: WebElement?
    var likeBtn: WebElement?

    init{
        shareBtn = controlsBlock.findElement(By.className("share"))
        replyBtn = controlsBlock.findElement(By.className("reply"))
        reblogBtn = controlsBlock.findElement(By.className("reblog"))
        likeBtn = controlsBlock.findElement(By.className("like"))
    }
}