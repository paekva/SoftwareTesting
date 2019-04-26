package TestCases

import Elements.popups.ReblogPopup
import Utils.printErrorMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class ReblogPopupTest(private val driver: WebDriver){
    private var popup: ReblogPopup = ReblogPopup(driver)

    // #4.4.1
    fun testAuthorLabel(){
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
    fun testChangeFieldToHTMLFormat(){
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
    fun testChangeFieldToTextFormat(){
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
    fun testReblogCancel(){
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
    fun testReblogPublicationSettings(){
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
    fun testReblogSubmit(){
        try{
            popup.reblog()
            printSuccessMsg("testReblogSubmit")
        }
        catch(e:Exception){
            printErrorMsg("testReblogSubmit",e.message)
        }
    }
}