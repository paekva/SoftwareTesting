package testCases

import elements.Header
import Utils.printErrorMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class DialogPopupTests(private val driver: WebDriver){

    // #
    fun authorProfileTest(){
        try{
            val header = Header(driver)
            val popup = header.openMessagingPopup()
            val dialog = popup
                .writeNewMsg()
                .findRecipient("pesosina")
                .chooseFoundRecipient()

            val authorProfile = dialog.seeAuthor()
            println("opened popup")
            authorProfile.closePopup()

            dialog.closePopup()
            printSuccessMsg("authorProfileTest")
        }
        catch(e: Exception){
            printErrorMsg("searchedRecipientMessageTest", e.message)
        }
    }

    // #
    fun minimizeDialogTest(){
        try{
            val header = Header(driver)
            val popup = header.openMessagingPopup()
            val dialog = popup
                .writeNewMsg()
                .findRecipient("pesosina")
                .chooseFoundRecipient()

            dialog
                .minimizeDialog()
                .maximizeDialog()
                .closePopup()

            printSuccessMsg("minimizeDialogTest")
        }
        catch(e: Exception){
            printErrorMsg("minimizeDialogTest", e.message)
        }
    }

    // #
    fun emptyInputTest(){
        try{
            val header = Header(driver)
            val popup = header.openMessagingPopup()
            val dialog = popup
                .writeNewMsg()
                .findRecipient("pesosina")
                .chooseFoundRecipient()

            dialog.inputMsg("")

            if(dialog.isSendButtonEnabled())
                throw Exception("send btn supposed to be disabled if no msg exists")

            dialog.closePopup()
            printSuccessMsg("emptyInputTest")
        }
        catch(e: Exception){
            printErrorMsg("emptyInputTest", e.message)
        }
    }

    // #
    fun correctInputTest(){
        try{
            val header = Header(driver)
            val popup = header.openMessagingPopup()
            val dialog = popup
                .writeNewMsg()
                .findRecipient("pesosina")
                .chooseFoundRecipient()

            dialog
                .inputMsg("Hello :)")
                .sendMsg()

            if(!dialog.isSendButtonEnabled())
                throw Exception("send btn supposed to be enabled")

            dialog.closePopup()
            printSuccessMsg("correctInputTest")
        }
        catch(e: Exception){
            printErrorMsg("correctInputTest", e.message)
        }
    }

    // #
    fun sendGifTest(){
        try{
            throw Exception("FINISH TEST")

            printSuccessMsg("sendGifTest")
        }
        catch(e: Exception){
            printErrorMsg("sendGifTest", e.message)
        }
    }

    // #
    fun sendStickerTest(){
        try{
            throw Exception("FINISH TEST")

            printSuccessMsg("sendStickerTest")
        }
        catch(e: Exception){
            printErrorMsg("sendStickerTest", e.message)
        }
    }

    // #
    fun cancelOfSendStickerTest(){
        try{
            throw Exception("FINISH TEST")

            printSuccessMsg("cancelOfSendStickerTest")
        }
        catch(e: Exception){
            printErrorMsg("cancelOfSendStickerTest", e.message)
        }
    }
}