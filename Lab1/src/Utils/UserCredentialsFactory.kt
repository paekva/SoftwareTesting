package Utils

class UserCredentialsFactory {
    fun getCorrectEmail(): String {
        return "paekva@yandex.ru"
    }

    fun getUnregisteredEmail(): String {
        return "someEmail@yandex.ru"
    }

    fun getIncorrectFormatEmail(): String {
        return "paekvayandexru"
    }

    fun getCorrectPassword(): String {
        return "rfnz98grf"
    }

    fun getIncorrectPassword(): String {
        return "12345"
    }

    fun getCorrectName(): String {
        return "12Hjrf9"
    }

    fun getUsedByOtheUserName(): String {
        return "Kate"
    }

    fun getEmptyString(): String {
        return ""
    }
}

