package TestCases

import Elements.popups.SharePopup
import Utils.printErrorMsg
import Utils.printSuccessMsg
import org.openqa.selenium.WebDriver

class SharePopupTest(private val driver: WebDriver){
    private var popup: SharePopup = SharePopup(driver)

    // #4.2.1
    fun testShareByPermLink(){
        try{
            popup.usePermLink()
            popup.returnFromPermLink()
            printSuccessMsg("testShareByStaticLink")
        }
        catch(e:Exception){
            printErrorMsg("testShareByStaticLink",e.message)
        }
    }

    // #4.2.2
    fun testShareByCopyPermLink(){
        try{
            popup.useCopyPermLink()
            popup.returnFromCopyPermLink()
            printSuccessMsg("testShareByCopyPermLink")
        }
        catch(e:Exception){
            printErrorMsg("testShareByCopyPermLink",e.message)
        }
    }

    // #4.2.3
    fun testShareByEmbed(){
        try{
            popup.useEmbed()
            popup.returnFromEmbed()
            printSuccessMsg("testShareByEmbed")
        }
        catch(e:Exception){
            printErrorMsg("testShareByEmbed",e.message)
        }
    }

    // #4.2.4
    fun testShareByEmail_EmptyInput(){
        try{
            val email = popup!!.useEmail()
            email!!.fillInEmail("")
            email.fillInMsg("")

            if(email.isSubmitEnabled())
                throw Exception("submit btn supposed to be disabled")

            popup.returnFromEmail()
            printSuccessMsg("testShareByEmail_EmptyInput")
        }
        catch(e:Exception){
            printErrorMsg("testShareByEmail_EmptyInput",e.message)
        }
    }

    // #4.2.5
    fun testShareByEmail_CorrectInput(){
        try{
            val email = popup.useEmail()
            email!!.fillInEmail("paekva@yandex.ru")
            email.fillInMsg("Hello!")

            if(!email.isSubmitEnabled())
                throw Exception("submit btn supposed to be unabled")

            popup.returnFromEmail()

            printSuccessMsg("testShareByEmail_CorrectInput")
        }
        catch(e:Exception){
            printErrorMsg("testShareByEmail_CorrectInput",e.message)
        }
    }

    // #4.2.7
    fun testShareComplain(){
        try{
            popup!!.useAbuse()
            popup.returnFromAbuse()
            printSuccessMsg("testShareComplain")
        }
        catch(e:Exception){
            printErrorMsg("testShareComplain",e.message)
        }
    }
}