package testCases

import elements.popups.ReplyPopup
import Utils.printErrorMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class ReplayPopupTest(private val driver: WebDriver){
    private var popup: ReplyPopup = ReplyPopup(driver)

    // #4.3.1
    fun testEmptyInput(){
        try{
            popup.fillTestField("")

            if(popup.isSendMsgBtnEnabled())
               throw Exception("button should be disabled")

            printSuccessMsg("testEmptyInput")
        }
        catch(e:Exception){
            printErrorMsg("testEmptyInput",e.message)
        }
    }

    // #4.3.2
    fun testCorrectInput(){
        try{
            popup.fillTestField("Hello!")

            if(!popup.isSendMsgBtnEnabled())
                throw Exception("button should be enabled")

            popup.sendMsg()

            printSuccessMsg("testCorrectInput")
        }
        catch(e:Exception){
            printErrorMsg("testCorrectInput",e.message)
        }
    }
}