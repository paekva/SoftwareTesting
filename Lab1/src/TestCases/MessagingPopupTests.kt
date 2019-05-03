package TestCases

import Elements.Header
import Utils.printErrorMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver


class MessagingPopupTests(private val driver: WebDriver){

    private val header = Header(driver)
    // #
    fun searchedRecipientMessageTest(){
        try{
            val popup = header.openMessagingPopup()

            val result = popup
                .writeNewMsg()
                .findRecipient("Another search")
                .chooseFoundRecipient()

            result.closePopup()
            printSuccessMsg("searchedRecipientMessageTest")
        }
        catch(e: Exception){
            printErrorMsg("searchedRecipientMessageTest", e.message)
        }
    }

    // #
    fun offeredRecipientMessageTest(){
        try{
            val popup = header.openMessagingPopup()
            val result = popup.chooseOfferedRecipient()

            result.closePopup()
            printSuccessMsg("offeredRecipientMessageTest")
        }
        catch(e: Exception){
            printErrorMsg("offeredRecipientMessageTest", e.message)
        }
    }

    // #
    fun cancelMessageTest(){
        try{
            val popup = header.openMessagingPopup()

            popup.writeNewMsg()
                .findRecipient("Search")
                .cancelNewMsg()
                .closePopup()

            printSuccessMsg("cancelMessageTest")
        }
        catch(e: Exception){
            printErrorMsg("cancelMessageTest", e.message)
        }
    }
}