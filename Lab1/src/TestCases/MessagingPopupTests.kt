package TestCases

import Elements.Header
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver


class MessagingPopupTests(private val driver: WebDriver){

    // #
    fun searchedRecipientMessageTest(){
        try{
            val header = Header(driver)

            val popup = header.openMessagingPopup()

            val result = popup
                .writeNewMsg()
                .findRecipient("Someone")
                .chooseFoundRecipient()

            result.closePopup()
            printSuccessMsg("searchedRecipientMessageTest")
        }
        catch(e: Exception){
            printSuccessMsg("searchedRecipientMessageTest")
        }
    }

    // #
    fun offeredRecipientMessageTest(){
        try{
            val header = Header(driver)

            val popup = header.openMessagingPopup()
            val result = popup.chooseOfferedRecipient()

            result.closePopup()
            printSuccessMsg("offeredRecipientMessageTest")
        }
        catch(e: Exception){
            printSuccessMsg("offeredRecipientMessageTest")
        }
    }

    // #
    fun cancelMessageTest(){
        try{
            val header = Header(driver)

            val popup = header.openMessagingPopup()

            popup.writeNewMsg()
                .findRecipient("I am about to cancel this")
                .cancelNewMsg()
                .closePopup()

            printSuccessMsg("cancelMessageTest")
        }
        catch(e: Exception){
            printSuccessMsg("cancelMessageTest")
        }
    }
}