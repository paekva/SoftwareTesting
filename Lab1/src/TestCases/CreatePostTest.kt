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
        printInfoMsg("CREATE POST POPUP test")
        textPostOptionTest()
        photoPostOptionTest()
        quotePostOptionTest()
        chatPostOptionTest()
        linkPostOptionTest()
        audioPostOptionTest()
        videoPostOptionTest()
        printInfoMsg("CREATE POST POPUP test FINSHED")
    }

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
        val wait = WebDriverWait(driver, 10)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Создать пост']")))
    }

    private fun closeCreatePostMenu(){
        pressEscKey(driver)
    }
}