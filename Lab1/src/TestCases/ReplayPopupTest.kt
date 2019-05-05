package testCases

import elements.popups.ReplyPopup
import Utils.printErrorMsg
import Utils.printSuccessMsg
import elements.Post
import org.openqa.selenium.WebDriver

class ReplayPopupTest(private val driver: WebDriver, private var post: Post, private var popup: ReplyPopup){

    fun runAllTests(){
        testEmptyInput()
        post.closePopup()

        post.openReplyPopup()
        testCorrectInput()
    }

    // #4.3.1
    private fun testEmptyInput(){
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
    private fun testCorrectInput(){
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