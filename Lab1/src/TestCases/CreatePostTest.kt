package testCases

import elements.CreatePostPopup
import elements.Header
import Utils.pressEscKey
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class CreatePostTest(private val driver: WebDriver, private val header: Header, private val popup: CreatePostPopup) {

    fun runAllTests(){
        printInfoMsg("CREATE POST MENU tests")

        textPostOptionTest()
        photoPostOptionTest()
        quotePostOptionTest()
        chatPostOptionTest()
        linkPostOptionTest()
        audioPostOptionTest()
        videoPostOptionTest()
    }

    // #5.1
    private fun textPostOptionTest(){
        try{
            val result = popup.createTextPost()
            result.closePopup()
            waitForCloseOfPopovers()
            header.openCreatePostPopup()

            printSuccessMsg("textPostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("textPostOptionTest", "cannot find text post popup")
        }
    }

    // #5.2
    private fun photoPostOptionTest(){
        try{
            val result = popup.createPhotoPost()
            result.closePopup()
            waitForCloseOfPopovers()
            header.openCreatePostPopup()

            printSuccessMsg("photoPostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("photoPostOptionTest", "cannot find photo post popup")
        }
    }

    // #5.3
    private fun quotePostOptionTest(){
        try{
            val result = popup.createQuotePost()
            result.closePopup()
            waitForCloseOfPopovers()
            header.openCreatePostPopup()

            printSuccessMsg("quotePostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("quotePostOptionTest", "cannot find quote post popup")
        }
    }

    // #5.4
    private fun linkPostOptionTest(){
        try{
            val result = popup.createLinkPost()
            result.closePopup()
            waitForCloseOfPopovers()
            header.openCreatePostPopup()

            printSuccessMsg("linkPostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("linkPostOptionTest", "cannot find link post popup")
        }
    }

    // #5.5
    private fun chatPostOptionTest(){
        try{
            val result = popup.createChatPost()
            result.closePopup()
            waitForCloseOfPopovers()
            header.openCreatePostPopup()

            printSuccessMsg("chatPostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("chatPostOptionTest", "cannot find chat post popup")
        }
    }

    // #5.6
    private fun audioPostOptionTest(){
        try{
            val result = popup.createAudioPost()
            result.closePopup()
            waitForCloseOfPopovers()
            header.openCreatePostPopup()

            printSuccessMsg("audioPostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("audioPostOptionTest", "cannot find audio post popup")
        }
    }

    // #5.7
    private fun videoPostOptionTest(){
        try{
            val result = popup.createVideoPost()
            result.closePopup()
            waitForCloseOfPopovers()
            header.openCreatePostPopup()

            printSuccessMsg("videoPostOptionTest")
        }
        catch (e: Exception){
            closeCreatePostMenu()
            printErrorMsg("videoPostOptionTest", "cannot find video post popup")
        }
    }

    private fun waitForCloseOfPopovers(){
        val wait = WebDriverWait(driver, 60)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Создать пост']")))
    }

    private fun closeCreatePostMenu(){
        pressEscKey(driver)
    }
}