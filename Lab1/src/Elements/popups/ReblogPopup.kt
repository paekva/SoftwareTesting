package Elements.popups

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
class ReblogPopup(private val popupElement: WebElement, private val driver: WebDriver){

    private var closeBtn: WebElement? = null
    var leftControl: WebElement? = null
    var rightControl: WebElement? = null
    var reblogTrial: WebElement? = null
    var textField: WebElement? = null
    private var reblogBtn: WebElement? = null
    var dropdownBtn: WebElement? = null
    private var footer: WebElement? = null

    init{
        println("creating reblog 1")
        leftControl = popupElement.findElement(By.className("tumblelog-select"))
        println("creating reblog 2")
        rightControl = popupElement.findElement(By.className("post-settings"))
        println("creating reblog 3")
        reblogTrial = popupElement.findElement(By.className("control-reblog-trail"))
        println("creating reblog 4")
        textField = popupElement.findElement(By.className("editor"))
        println("creating reblog 5")
        footer = popupElement.findElement(By.className("post-form--footer"))
        println("creating reblog 6")
        closeBtn = popupElement.findElement(By.className("tx-button"))
        println("creating reblog 7")
        reblogBtn = popupElement.findElement(By.className("create_post_button"))
        println("creating reblog 8")
        dropdownBtn = popupElement.findElement(By.className("dropdown-area"))
    }

    fun closePopup(){
        closeBtn!!.click()

        driver.findElement(By.className("btn_1")).click()
    }
}
