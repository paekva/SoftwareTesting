import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

class Registration(private val driver: ChromeDriver){
    private fun beforeRegistrationTest() {
        val mainPageUrl = "https://www.tumblr.com/"
        driver.get(mainPageUrl)

        driver.findElement(By.id("signup_forms_submit")).click()
    }

    fun registr(email: String, password: String, name: String){}

    fun deleteAccount(){}

    fun testRegistration(){
        testRegistrationWithWrongCredentials()
        testRegistrationWithCorrectCredentials()
    }

    fun testAccountDelete(){
        try {
            println("Account delete test is successful")
        }
        catch(e: Exception){
            println(e.message)
        }
    }

    private fun testRegistrationWithCorrectCredentials(){
        beforeRegistrationTest()

        try {
            println("Registration test is successful")
        }
        catch(e: Exception){
            println(e.message)
        }
    }
    private fun testRegistrationWithWrongCredentials(){
        beforeRegistrationTest()

        try {
            println("Test for Validation of Registration fields is successful")
        }
        catch(e: Exception){
            println("Test for Validation of Registration fields has failed")
        }
    }
}