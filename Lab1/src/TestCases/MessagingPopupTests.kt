package testCases

import elements.Header
import elements.popups.MessagingPopup
import Utils.printErrorMsg
import Utils.printInfoMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver


class MessagingPopupTests(private val driver: WebDriver, private val header: Header, private val popup: MessagingPopup){

    fun runAllTests(){
        printInfoMsg("MESSAGES tests")
        cancelMessageTest()
        searchedRecipientMessageTest()
        printInfoMsg("MESSAGES tests FINISHED")
    }

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

    private fun cancelMessageTest(){
        try{
            popup
                .writeNewMsg()
                .findRecipient("Search")
                .cancelNewMsg()
                .close()

            header.openMessagingPopup()
            printSuccessMsg("cancelMessageTest")
        }
        catch(e: Exception){
            printErrorMsg("cancelMessageTest", e.message)
        }
    }
}