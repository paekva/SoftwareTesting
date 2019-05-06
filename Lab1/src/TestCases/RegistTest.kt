package testCases

import Utils.*
import pages.RegisterPage
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class RegistTest(private val driver: WebDriver){
    private val registPage: RegisterPage
    private val userCredentials: UserCredentialsFactory
    private val url = "https://www.tumblr.com/register"

    init{
        beforeTest()
        registPage = RegisterPage(driver)
        userCredentials = UserCredentialsFactory()
    }

    fun runAllTests(){
        printInfoMsg("REGISTRATION tests")

        registWithEmptyFieldsTest()

        beforeTest()
        registWithIncorrectFormatEmailTest()
        
        beforeTest()
        registWithUsedEmailTest()

        beforeTest()
        registWithTooShortPasswordTest()

        beforeTest()
        registWithRestrictedSymbolsInNameTest()

        beforeTest()
        registWithNameUsedPreviouslyTest()

        beforeTest()
        registWithRestrictedSymbolsInAgeTest()

        beforeTest()
        registWithoutCheckingInRulesTest()

        /*beforeTest()
        correctRegistTest()*/
        
        printInfoMsg("REGISTRATION tests FINISHED")
    }

    private fun beforeTest(){
        driver.get(url)
        driver.findElement(By.xpath("//*[@id=\"signup_forms_submit\"]")).click()

        val wait = WebDriverWait(driver, 20)
        wait.until<WebElement>(ExpectedConditions.elementToBeClickable(By.id("signup_email")))
    }

    // #2.1
    private fun registWithEmptyFieldsTest() {
        try{
            registPage
                .fillInPassword(userCredentials.getEmptyString())
                .fillInEmail(userCredentials.getEmptyString())
                .fillInName(userCredentials.getEmptyString())
                .forward()

            if(registPage.getError() == null)
                throw Exception("no error message presented")

            if(driver.currentUrl != url)
                throw Exception("supposed to remain on registration page")

            printSuccessMsg("registWithEmptyFieldsTest")
        }
        catch(e: Exception){
            printErrorMsg("registWithEmptyFieldsTest", e.message)
        }
    }

    private fun registWithIncorrectFormatEmailTest() {
        try{
            registPage
                .fillInPassword(userCredentials.getCorrectPassword())
                .fillInEmail(userCredentials.getIncorrectFormatEmail())
                .fillInName(userCredentials.getCorrectName())
                .forward()

            if(registPage.getError() == null)
                throw Exception("no error message presented")

            if(driver.currentUrl != url)
                throw Exception("supposed to remain on registration page")

            printSuccessMsg("registWithIncorrectFormatEmailTest")
        }
        catch(e: Exception){
            printErrorMsg("registWithIncorrectFormatEmailTest", e.message)
        }
    }

    private fun registWithUsedEmailTest() {
        try{
            registPage
                .fillInPassword(userCredentials.getEmptyString())
                .fillInEmail(userCredentials.getCorrectEmail())
                .fillInName(userCredentials.getCorrectName())
                .forward()

            if(registPage.getError() == null)
                throw Exception("no error message presented")

            if(driver.currentUrl != url)
                throw Exception("supposed to remain on registration page")

            printSuccessMsg("registWithUsedEmailTest")
        }
        catch(e: Exception){
            printErrorMsg("registWithUsedEmailTest", e.message)
        }
    }

    private fun registWithTooShortPasswordTest() {
        try{
            registPage
                .fillInPassword(userCredentials.getIncorrectPassword())
                .fillInEmail(userCredentials.getNewCorrectEmail())
                .fillInName(userCredentials.getCorrectName())
                .forward()

            if(registPage.getError() == null)
                throw Exception("no error message presented")

            if(driver.currentUrl != url)
                throw Exception("supposed to remain on registration page")

            printSuccessMsg("registWithTooShortPasswordTest")
        }
        catch(e: Exception){
            printErrorMsg("registWithTooShortPasswordTest", e.message)
        }
    }

    private fun registWithRestrictedSymbolsInNameTest() {
        try{
            registPage
                .fillInPassword(userCredentials.getCorrectPassword())
                .fillInEmail(userCredentials.getNewCorrectEmail())
                .fillInName(userCredentials.getRestrictedSymbolsString())
                .forward()

            if(registPage.getError() == null)
                throw Exception("no error message presented")

            if(driver.currentUrl != url)
                throw Exception("supposed to remain on registration page")

            printSuccessMsg("registWithRestrictedSymbolsInNameTest")
        }
        catch(e: Exception){
            printErrorMsg("registWithRestrictedSymbolsInNameTest", e.message)
        }
    }

    private fun registWithNameUsedPreviouslyTest() {
        try{
            registPage
                .fillInPassword(userCredentials.getCorrectPassword())
                .fillInEmail(userCredentials.getNewCorrectEmail())
                .fillInName(userCredentials.getUsedByOtheUserName())
                .forward()

            if(registPage.getError() == null)
                throw Exception("no error message presented")

            if(driver.currentUrl != url)
                throw Exception("supposed to remain on registration page")

            printSuccessMsg("registWithNameUsedPreviouslyTest")
        }
        catch(e: Exception){
            printErrorMsg("registWithNameUsedPreviouslyTest", e.message)
        }
    }

    private fun registWithRestrictedSymbolsInAgeTest() {
        try{
            registPage
                .fillInPassword(userCredentials.getNewCorrectPassword())
                .fillInEmail(userCredentials.getNewCorrectEmail())
                .fillInName(userCredentials.getCorrectName())
                .forward()
                .fillInAge(userCredentials.getRestrictedSymbolsString())
                .checkInRules()
                .forward()


            if(registPage.getError() == null)
                throw Exception("no error message presented")

            if(driver.currentUrl != url)
                throw Exception("supposed to remain on registration page")

            printSuccessMsg("registWithRestrictedSymbolsInAgeTest")
        }
        catch(e: Exception){
            printErrorMsg("registWithRestrictedSymbolsInAgeTest", e.message)
        }
    }

    private fun registWithoutCheckingInRulesTest() {
        try{
            registPage
                .fillInPassword(userCredentials.getNewCorrectPassword())
                .fillInEmail(userCredentials.getNewCorrectEmail())
                .fillInName(userCredentials.getCorrectName())
                .forward()
                .fillInAge(userCredentials.getCorrectAge())
                .forward()

            if(registPage.getError() == null)
                throw Exception("no error message presented")

            if(driver.currentUrl != url)
                throw Exception("supposed to remain on registration page")

            printSuccessMsg("registWithoutCheckingInRulesTest")
        }
        catch(e: Exception){
            printErrorMsg("registWithoutCheckingInRulesTest", e.message)
        }
    }

    private fun correctRegistTest() {
        try{
            registPage
                .fillInPassword(userCredentials.getNewCorrectPassword())
                .fillInEmail(userCredentials.getNewCorrectEmail())
                .fillInName(userCredentials.getCorrectName())
                .forward()
                .fillInAge(userCredentials.getCorrectAge())
                .checkInRules()
                .forward()
                .doRecapcha()
                .regist()

            if(registPage.getError() == null)
                throw Exception("no error message presented")

            if(driver.currentUrl != url)
                throw Exception("supposed to remain on registration page")

            printSuccessMsg("correctRegistTest")
        }
        catch(e: Exception){
            printErrorMsg("correctRegistTest", e.message)
        }
    }
}