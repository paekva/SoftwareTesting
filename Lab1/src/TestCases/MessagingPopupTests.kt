package TestCases

import Elements.Header
import Elements.popups.MessagingPopup
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver


class MessagingPopupTests(private val driver: WebDriver, private val header: Header, private val popup: MessagingPopup){

    fun runAllTests(){
        printInfoMsg("MESSAGES tests")
        cancelMessageTest()
        searchedRecipientMessageTest()
        offeredRecipientMessageTest()
    }

    // #
    private fun searchedRecipientMessageTest(){
        try{
            val result = popup
                .writeNewMsg()
                .findRecipient("Another search")
                .chooseFoundRecipient()

            result.closePopup()

            header.openMessagingPopup()
            printSuccessMsg("searchedRecipientMessageTest")
        }
        catch(e: Exception){
            printErrorMsg("searchedRecipientMessageTest", e.message)
        }
    }

    // #
    private fun offeredRecipientMessageTest(){
        try{
            val result = popup.chooseOfferedRecipient()

            result.closePopup()

            header.openMessagingPopup()
            printSuccessMsg("offeredRecipientMessageTest")
        }
        catch(e: Exception){
            printErrorMsg("offeredRecipientMessageTest", e.message)
        }
    }

    // #
    private fun cancelMessageTest(){
        try{
            popup
                .writeNewMsg()
                .findRecipient("Search")
                .cancelNewMsg()
                .closePopup()

            header.openMessagingPopup()
            printSuccessMsg("cancelMessageTest")
        }
        catch(e: Exception){
            printErrorMsg("cancelMessageTest", e.message)
        }
    }
}