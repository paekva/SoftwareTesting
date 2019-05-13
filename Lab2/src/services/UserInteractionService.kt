package services

class UserInteractionService {

    fun textInputField(value: String): Boolean{
        return value.toLowerCase().matches("[a-z]+".toRegex())
    }

    fun commandInputField(value: String): Boolean{
        return value.matches("[1-5]".toRegex())
    }
}