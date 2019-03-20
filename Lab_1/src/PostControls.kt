import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class PostControls(private val controlsBlock: WebElement){

    public fun test(){

        val shareBtn = controlsBlock.findElement(By.className("share"))
        val replyBtn = controlsBlock.findElement(By.className("reply"))
        val reblogBtn = controlsBlock.findElement(By.className("reblog"))
        val likeBtn = controlsBlock.findElement(By.className("like"))
    }
}