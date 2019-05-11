package testCases

import elements.popups.ReblogPopup
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class ReblogPopupTest(private val driver: WebDriver){
    private var popup: ReblogPopup = ReblogPopup(driver)

    fun runAllTests(){
        printInfoMsg("\nPOST test: REBLOG POPUP test")
        testAuthorLabel()
        testChangeFieldToHTMLFormat()
        testChangeFieldToTextFormat()
        testReblogPublicationSettings()
        // testReblogCancel()
        testReblogSubmit()
        printInfoMsg("\tPOST test: REBLOG POPUP test FINISHED")
    }

    // #4.4.1
    private fun testAuthorLabel(){
        try{
            popup.openAuthorLabel()
            popup.closeAuthorLabel()
            printSuccessMsg("testAuthorLabel")
        }
        catch(e:Exception){
            printErrorMsg("testAuthorLabel",e.message)
        }
    }

    // #4.4.2
    private fun testChangeFieldToHTMLFormat(){
        try{
            val settingsPopup = popup.openSettings()
            settingsPopup.chooseHTMLFormat()

            if(popup.getHtmlFormatField() == null)
                throw Exception("the field did not change its type")

            popup.closeSettings()
            printSuccessMsg("testChangeFieldToHTMLFormat")
        }
        catch(e:Exception){
            printErrorMsg("testChangeFieldToHTMLFormat",e.message)
        }
    }

    // #4.4.3
    private fun testChangeFieldToTextFormat(){
        try{
            val settingsPopup = popup.openSettings()
            settingsPopup.chooseFormattedText()

            if(popup.getTextFormatField() == null)
                throw Exception("the field did not change its type")

            popup.closeSettings()
            printSuccessMsg("testChangeFieldToTextFormat")
        }
        catch(e:Exception){
            printErrorMsg("testChangeFieldToTextFormat",e.message)
        }
    }

    // #4.4.4
    private fun testReblogCancel(){
        try{
            popup.fillInMsgField("Message")
            popup.closePopup()

            printSuccessMsg("testReblogCancel")
        }
        catch(e:Exception){
            printErrorMsg("testReblogCancel",e.message)
        }
    }

    // #4.4.5
    private fun testReblogPublicationSettings(){
        try{
            popup.openDropdown()
            popup.closeDropdown()
            printSuccessMsg("testReblogPublicationSettings")
        }
        catch(e:Exception){
            printErrorMsg("testReblogPublicationSettings",e.message)
        }
    }

    // #4.4.6
    private fun testReblogSubmit(){
        try{
            popup
                .fillInMsgField("Message")
                .reblog()

            val action = Actions(driver)
            action.sendKeys(Keys.ENTER).perform()

            printSuccessMsg("testReblogSubmit")
        }
        catch(e:Exception){
            driver.switchTo().alert().accept()
            printErrorMsg("testReblogSubmit",e.message)
        }
    }
}