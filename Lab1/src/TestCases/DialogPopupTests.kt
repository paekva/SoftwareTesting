package testCases

import elements.Header
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class DialogPopupTests(private val driver: WebDriver){

    fun runAllTests(){
        printInfoMsg("DIALOG tests")
        authorProfileTest()
        minimizeDialogTest()
        emptyInputTest()
        sendOptionsTest()
        correctInputTest()
        printInfoMsg("DIALOG tests FINISHED")
    }

    private fun authorProfileTest(){
        try{
            val header = Header(driver)
            val popup = header.openMessagingPopup()
            val dialog = popup
                .writeNewMsg()
                .findRecipient("pesosina")
                .chooseFoundRecipient()

            val authorProfile = dialog.seeAuthor()
            authorProfile.closePopup()

            dialog.closePopup()
            printSuccessMsg("authorProfileTest")
        }
        catch(e: Exception){
            printErrorMsg("authorProfileTest", e.message)
        }
    }

    private fun minimizeDialogTest(){
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

    private fun emptyInputTest(){
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

    private fun correctInputTest(){
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

            dialog.closePopup()
            printSuccessMsg("correctInputTest")
        }
        catch(e: Exception){
            printErrorMsg("correctInputTest", e.message)
        }
    }

    private fun sendOptionsTest(){
        try{
            val header = Header(driver)
            val popup = header.openMessagingPopup()
            val dialog = popup
                .writeNewMsg()
                .findRecipient("pesosina")
                .chooseFoundRecipient()

            dialog
                .attachGif()
                .closeOptionPopup()
                .attachSticker()
                .closeOptionPopup()

            dialog.closePopup()
            printSuccessMsg("sendOptionsTest")
        }
        catch(e: Exception){
            printErrorMsg("sendOptionsTest", e.message)
        }
    }

}