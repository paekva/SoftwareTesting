package testCases

import elements.Header
import Utils.printErrorMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class DialogPopupTests(private val driver: WebDriver){

    fun runAllTests(){
        authorProfileTest()
        minimizeDialogTest()
        settingsTest()
        emptyInputTest()
        sendOptionsTest()
        correctInputTest()
    }

    // #
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

    // #
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

    private fun settingsTest(){
        try{
            val header = Header(driver)
            val popup = header.openMessagingPopup()
            val dialog = popup
                .writeNewMsg()
                .findRecipient("pesosina")
                .chooseFoundRecipient()

            dialog
                .openSettings()
                .closeOptionPopup()

            dialog.closePopup()
            printSuccessMsg("settingsTest")
        }
        catch(e: Exception){
            printErrorMsg("settingsTest", e.message)
        }
    }

    // #
    private fun emptyInputTest(){
        try{
            val header = Header(driver)
            val popup = header.openMessagingPopup()
            val dialog = popup
                .writeNewMsg()
                .findRecipient("pesosina")
                .chooseFoundRecipient()

            dialog.inputMsg("")
            println("this works")
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