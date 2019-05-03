package TestCases

import Elements.CreatePostPopup
import Elements.Header
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class CreatePostTest(private val driver: WebDriver, private val header: Header, private val popup: CreatePostPopup) {

    fun runAllTests(){
        printInfoMsg("\tCREATE POST MENU tests")

        textPostOptionTest()

        header.openCreatePostPopup()
        photoPostOptionTest()

        header.openCreatePostPopup()
        quotePostOptionTest()

        header.openCreatePostPopup()
        chatPostOptionTest()

        header.openCreatePostPopup()
        linkPostOptionTest()

        header.openCreatePostPopup()
        audioPostOptionTest()

        header.openCreatePostPopup()
        videoPostOptionTest()
    }

    // #5.1
    fun textPostOptionTest(){
        try{
            val result = popup.createTextPost()
            result.closePopup()
            waitForCloseOfPopovers()

            printSuccessMsg("textPostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("textPostOptionTest", "cannot find text post popup")
        }
    }

    // #5.2
    fun photoPostOptionTest(){
        try{
            val result = popup.createPhotoPost()
            result.closePopup()
            waitForCloseOfPopovers()

            printSuccessMsg("photoPostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("photoPostOptionTest", "cannot find photo post popup")
        }
    }

    // #5.3
    fun quotePostOptionTest(){
        try{
            val result = popup.createQuotePost()
            result.closePopup()
            waitForCloseOfPopovers()

            printSuccessMsg("quotePostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("quotePostOptionTest", "cannot find quote post popup")
        }
    }

    // #5.4
    fun linkPostOptionTest(){
        try{
            val result = popup.createLinkPost()
            result.closePopup()
            waitForCloseOfPopovers()

            printSuccessMsg("linkPostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("linkPostOptionTest", "cannot find link post popup")
        }
    }

    // #5.5
    fun chatPostOptionTest(){
        try{
            val result = popup.createChatPost()
            result.closePopup()
            waitForCloseOfPopovers()

            printSuccessMsg("chatPostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("chatPostOptionTest", "cannot find chat post popup")
        }
    }

    // #5.6
    fun audioPostOptionTest(){
        try{
            val result = popup.createAudioPost()
            result.closePopup()
            waitForCloseOfPopovers()

            printSuccessMsg("audioPostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("audioPostOptionTest", "cannot find audio post popup")
        }
    }

    // #5.7
    fun videoPostOptionTest(){
        try{
            val result = popup.createVideoPost()
            result.closePopup()
            waitForCloseOfPopovers()

            printSuccessMsg("videoPostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("videoPostOptionTest", "cannot find video post popup")
        }
    }

    private fun waitForCloseOfPopovers(){
        val wait = WebDriverWait(driver, 40)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"home_button\"]/a")))
    }

    private fun closeCreatePostMenu(){
        val action = Actions(driver)
        action.sendKeys(Keys.ESCAPE).perform()
    }
}